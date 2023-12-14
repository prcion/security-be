package com.findork.preclinical.security.auth;

import com.findork.preclinical.features.account.User;
import com.findork.preclinical.features.account.UserConverter;
import com.findork.preclinical.features.confirmationtoken.ConfirmationToken;
import com.findork.preclinical.features.confirmationtoken.ConfirmationTokenService;
import com.findork.preclinical.security.jwt.JwtTokenProvider;
import com.findork.preclinical.security.payload.*;
import com.findork.preclinical.security.userdetails.CustomUserDetailsService;
import com.findork.preclinical.utils.TokenUtils;
import com.findork.preclinical.utils.httpresponse.HttpResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Auth Controller, An entry class for all incoming requests
 */
@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final AuthConverter authConverter;
    private final UserConverter userConverter;
    private final AuthService authService;
    private final HttpResponseUtil httpResponseUtil;
    private final CustomUserDetailsService customUserDetailsService;
    private final ConfirmationTokenService confirmationTokenService;


    /**
     * Validate the credentials and generate the jwt tokens
     *
     * @return access token and refresh token
     */
    @PostMapping("/login")
    public JwtAuthenticationResponse login(@RequestBody LoginRequest loginRequest) {

        var user = customUserDetailsService.loadUserEmail(loginRequest.getEmail());

        var userDetails = customUserDetailsService.loadUserByUsername(loginRequest.getEmail());


        authenticate(loginRequest.getEmail(), loginRequest.getPassword(), userDetails.getAuthorities());


        var accessJwt = tokenProvider.generateAccessToken(userDetails);

        return JwtAuthenticationResponse
                .builder()
                .accessToken(accessJwt)
                .userDetails(authConverter.fromUserToUserDetailsResponse(user))
                .build();
    }

    private void authenticate(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password, authorities));
    }

    /**
     * Validate the access token and returns user details
     *
     * @return status of token
     */
    @PostMapping("/user-details")
    public UserDetailsResponse details(@RequestBody JwtAuthenticationResponse request) {
        String email = tokenProvider.getEmailFromToken(request.getAccessToken());
        var user = customUserDetailsService.loadUserEmail(email);
        return authConverter.fromUserToUserDetailsResponse(user);
    }

    /**
     * This is for user registration
     *
     * @return user registration status
     */
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        authService.verifyIfEmailExists(registerRequest.getEmail());

        User user = userConverter.signUpRequestToUser(registerRequest);

        user = authService.save(user);

        String confirmToken = TokenUtils.generateConfirmationToken();

        confirmationTokenService.saveToken(new ConfirmationToken(confirmToken, user));


        return ResponseEntity.ok(httpResponseUtil.createHttpResponse(HttpStatus.CREATED, "User registered successfully"));
    }


    @PutMapping("/confirmation")
    public void confirmUserAccount(@Valid @RequestParam("email") String email, @RequestParam("token") String confirmationToken) {
        authService.validateTokenAndSetUserStatusToActive(confirmationToken, email);
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordRequest request, User user) {
        authService.changeUserPassword(request, user);

        return ResponseEntity.ok(httpResponseUtil.createHttpResponse(HttpStatus.OK,"User password changed successfully"));
    }
}

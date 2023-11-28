package com.servustech.skeleton.security.auth;

import com.servustech.skeleton.features.account.User;
import com.servustech.skeleton.features.account.UserConverter;
import com.servustech.skeleton.features.confirmationtoken.ConfirmationToken;
import com.servustech.skeleton.features.confirmationtoken.ConfirmationTokenService;
import com.servustech.skeleton.security.constants.AuthConstants;
import com.servustech.skeleton.security.handler.RequestHandler;
import com.servustech.skeleton.security.jwt.JwtTokenProvider;
import com.servustech.skeleton.security.payload.*;
import com.servustech.skeleton.security.userdetails.CustomUserDetailsService;
import com.servustech.skeleton.utils.TokenUtils;
import com.servustech.skeleton.utils.httpresponse.HttpResponseUtil;
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
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final RequestHandler requestHandler;
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
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        var userDetails = customUserDetailsService.loadUserByUsername(loginRequest.getUsername());


        authenticate(loginRequest.getUsername(), loginRequest.getPassword(), userDetails.getAuthorities());


        var accessJwt = tokenProvider.generateAccessToken(userDetails);

        return ResponseEntity.ok(JwtAuthenticationResponse.builder().accessToken(accessJwt).build());
    }

    private void authenticate(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password, authorities));
    }


    /**
     * Validate the access token and returns user details
     *
     * @return status of token
     */
    @GetMapping("/user-details")
    public ResponseEntity<?> details(@RequestHeader(AuthConstants.AUTH_KEY) String authToken) {
        String jwt = requestHandler.getJwtFromStringRequest(authToken);
        UserDetailsResponse response = tokenProvider.getUserNameAndRolesFromJWT(jwt);
        return ResponseEntity.ok(response);
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

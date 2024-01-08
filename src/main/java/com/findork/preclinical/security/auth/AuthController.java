package com.findork.preclinical.security.auth;

import com.findork.preclinical.exceptions.PermissionDeniedException;
import com.findork.preclinical.features.account.User;
import com.findork.preclinical.features.account.security_codes.SecurityCodeService;
import com.findork.preclinical.features.confirmation_token.ConfirmationToken;
import com.findork.preclinical.features.confirmation_token.ConfirmationTokenService;
import com.findork.preclinical.integrations.ThymeleafMailService;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

import static org.springframework.http.HttpHeaders.ORIGIN;

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
    private final ThymeleafMailService thymeleafMailService;
    private final AuthService authService;
    private final HttpResponseUtil httpResponseUtil;
    private final CustomUserDetailsService customUserDetailsService;
    private final ConfirmationTokenService confirmationTokenService;
    private final SecurityCodeService securityCodeService;
    private final PasswordEncoder passwordEncoder;

    /**
     * Validate the credentials and generate the jwt tokens
     *
     * @return access token and refresh token
     */
    @PostMapping("/login")
    public JwtAuthenticationResponse login(@RequestBody LoginRequest loginRequest) {

        var user = customUserDetailsService.loadUserEmail(loginRequest.getEmail());

        validatePassword(user.getPassword(), loginRequest.getPassword());

        if (user.isAllowTwoStepAuthentication()) {
            securityCodeService.createSecurityCode(user);
            return JwtAuthenticationResponse
                    .builder()
                    .httpStatusCode(666)
                    .build();
        }

        return authenticate(user, loginRequest.getEmail(), loginRequest.getPassword());
    }

    @PostMapping("/2fa")
    public JwtAuthenticationResponse confirm2FA(@RequestBody Login2FARequest request) {
        var user = customUserDetailsService.loadUserEmail(request.getEmail());

        validatePassword(user.getPassword(), request.getPassword());
        securityCodeService.validate(user, request.getSecurityCode());

        return authenticate(user, request.getEmail(), request.getPassword());
    }

    public void validatePassword(String password, String requestPassword) {
        if (!passwordEncoder.matches(requestPassword, password)) {
            throw new PermissionDeniedException("Passwords don't match");
        }
    }

    private JwtAuthenticationResponse authenticate(User user, String email, String password) {
        var userDetails = customUserDetailsService.loadUserByUsername(email);


        authenticate(email, password, userDetails.getAuthorities());


        var accessJwt = tokenProvider.generateAccessToken(userDetails);

        return JwtAuthenticationResponse
                .builder()
                .accessToken(accessJwt)
                .userDetails(authConverter.fromUserToUserDetailsResponse(user))
                .httpStatusCode(200)
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
    @PreAuthorize("hasAnyRole('COMPANY_ADMINISTRATOR', 'SYSTEM_ADMINISTRATOR')")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest, @RequestHeader(ORIGIN) URI origin) {
        authService.verifyIfEmailExists(registerRequest.getEmail());

        User user = authConverter.signUpRequestToUser(registerRequest);

        user = authService.save(user);

        String confirmToken = TokenUtils.generateConfirmationToken();

        confirmationTokenService.saveToken(new ConfirmationToken(confirmToken, user));

        System.out.println(origin);
        thymeleafMailService.sendActivationEmail(user, origin, confirmToken);

        return ResponseEntity.ok(httpResponseUtil.createHttpResponse(HttpStatus.CREATED, "User registered successfully"));
    }


    @PutMapping("/confirmation")
    public void confirmUserAccount(@Valid @RequestBody ConfirmationRequest request) {
        authService.confirmUserAccount(request);
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordRequest request, User user) {
        authService.changeUserPassword(request, user);

        return ResponseEntity.ok(httpResponseUtil.createHttpResponse(HttpStatus.OK,"User password changed successfully"));
    }
}

package com.findork.preclinical.features.account;

import com.findork.preclinical.security.payload.RegisterRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserConverter {
    private final PasswordEncoder passwordEncoder;

    public User signUpRequestToUser(RegisterRequest registerRequest) {
        return User
                .builder()
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .accountStatus(AccountStatus.PENDING)
                .accountType(AccountType.ROLE_USER)
                .build();
    }

}

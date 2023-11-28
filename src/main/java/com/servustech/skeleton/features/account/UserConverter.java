package com.servustech.skeleton.features.account;

import com.servustech.skeleton.security.payload.RegisterRequest;
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
                .username(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .accountStatus(AccountStatus.PENDING)
                .accountType(AccountType.ROLE_USER)
                .build();
    }

}

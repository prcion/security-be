package com.findork.preclinical.features.account;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserConverter {
    private final PasswordEncoder passwordEncoder;

}

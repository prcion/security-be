package com.findork.preclinical.config;

import com.findork.preclinical.features.account.UserRepository;
import com.findork.preclinical.features.account.domain.AccountStatus;
import com.findork.preclinical.features.account.domain.AccountType;
import com.findork.preclinical.features.account.domain.User;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class PostStartupSetup {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private static final String SYSTEM_ADMIN_EMAIL = "admin@admin.com";
    @PostConstruct
    public void postConstruct() {
        createSystemAdministrator();
    }

    private void createSystemAdministrator() {
        if (userRepository.findByEmail(SYSTEM_ADMIN_EMAIL).isEmpty()) {
            User user = userRepository.save(User.builder()
                    .email(SYSTEM_ADMIN_EMAIL)
                    .firstName("Policybase")
                    .lastName("System Admin")
                    .accountStatus(AccountStatus.ACTIVE)
                    .accountType(AccountType.ROLE_SYSTEM_ADMINISTRATOR)
                    .password(passwordEncoder.encode("Test1234"))
                    .build());
            userRepository.save(user);
        }
    }
}

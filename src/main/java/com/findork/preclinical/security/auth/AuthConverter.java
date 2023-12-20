package com.findork.preclinical.security.auth;

import com.findork.preclinical.features.account.AccountStatus;
import com.findork.preclinical.security.payload.RegisterRequest;
import com.findork.preclinical.security.payload.UserDetailsResponse;
import com.findork.preclinical.features.account.User;
import org.springframework.stereotype.Component;

@Component
public class AuthConverter {

    public UserDetailsResponse fromUserToUserDetailsResponse(User user) {
        return UserDetailsResponse
                .builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getFullName())
                .accountStatus(user.getAccountStatus())
                .accountType(user.getAccountType())
                .build();
    }

    public User signUpRequestToUser(RegisterRequest registerRequest) {
        return User
                .builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .accountStatus(AccountStatus.PENDING)
                .accountType(registerRequest.getAccountType())
                .companyId(registerRequest.getCompanyId())
                .build();
    }
}

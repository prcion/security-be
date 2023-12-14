package com.findork.preclinical.security.auth;

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
                .name(user.getName())
                .build();
    }
}

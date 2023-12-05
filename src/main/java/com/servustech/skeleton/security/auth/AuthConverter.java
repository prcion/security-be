package com.servustech.skeleton.security.auth;

import com.servustech.skeleton.features.account.User;
import com.servustech.skeleton.security.payload.UserDetailsResponse;
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

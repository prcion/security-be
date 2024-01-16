package com.findork.preclinical.features.account;

import com.findork.preclinical.features.account.domain.User;
import com.findork.preclinical.features.account.dto.UserAdministrationResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserAdministrationResponse fromEntityToAdministrationResponse(User user) {
        return UserAdministrationResponse
                .builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .accountStatus(user.getAccountStatus())
                .accountType(user.getAccountType())
                .allowTwoStepAuthentication(user.isAllowTwoStepAuthentication())
                .build();
    }
}

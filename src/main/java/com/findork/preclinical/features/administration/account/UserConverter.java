package com.findork.preclinical.features.administration.account;

import com.findork.preclinical.features.administration.account.domain.User;
import com.findork.preclinical.features.administration.account.dto.UserAdministrationResponse;
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
                .companyId(user.getCompanyId())
                .build();
    }
}

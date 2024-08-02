package com.findork.preclinical.features.account.dto;

import com.findork.preclinical.features.account.domain.AccountStatus;
import com.findork.preclinical.features.account.domain.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserAdministrationResponse {
    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private AccountStatus accountStatus;

    private AccountType accountType;

    private boolean allowTwoStepAuthentication;

    private String companyId;
}

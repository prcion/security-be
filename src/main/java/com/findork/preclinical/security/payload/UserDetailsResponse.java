package com.findork.preclinical.security.payload;

import com.findork.preclinical.features.account.AccountStatus;
import com.findork.preclinical.features.account.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDetailsResponse {
    private String id;
    private String name;
    private String email;

    private AccountStatus accountStatus;

    private AccountType accountType;
}

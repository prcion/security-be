package com.findork.preclinical.security.payload;

import com.findork.preclinical.exceptions.PermissionDeniedException;
import com.findork.preclinical.features.account.domain.AccountType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RegisterRequest {
    @NotBlank
    @Size(min = 2, max = 40)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 40)
    private String lastName;

    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    @NotNull
    private AccountType accountType;

    @NotNull
    private String companyId;

    public AccountType getAccountType() {
        if (accountType.isSystemAdministrator()) {
            throw new PermissionDeniedException("Account with System Administrator Role cannot be created.");
        }
        return accountType;
    }
}

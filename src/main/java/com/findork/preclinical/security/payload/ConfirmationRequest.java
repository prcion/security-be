package com.findork.preclinical.security.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ConfirmationRequest {

    @NotBlank
    private String confirmationToken;

    @NotBlank
    @Size(min = 6, max = 30)
    private String password;

    @NotBlank
    @Size(min = 6, max = 30)
    private String passwordConfirmation;
}

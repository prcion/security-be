package com.findork.preclinical.security.payload;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@AllArgsConstructor
public class ChangePasswordRequest {


    @Size(min = 6, max = 20)
    @NotBlank
    private final String oldPassword;

    @Size(min = 6, max = 20)
    @NotBlank
    private final String newPassword;

    @NotNull
    private final String username;

}

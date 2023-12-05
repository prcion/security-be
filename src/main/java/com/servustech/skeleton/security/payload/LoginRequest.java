package com.servustech.skeleton.security.payload;

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
public class LoginRequest {
    @NotBlank
    @Size(max = 140)
    private String email;

    @NotNull
    @Size(min = 2)
    private String password;
}

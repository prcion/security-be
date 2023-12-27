package com.findork.preclinical.security.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Login2FARequest extends LoginRequest {
    @NotBlank
    @Size(min = 5, max = 10)
    private String securityCode;
}

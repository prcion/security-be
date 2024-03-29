package com.findork.preclinical.security.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class JwtAuthenticationResponse {
    private String accessToken;
    private UserDetailsResponse userDetails;
    private int httpStatusCode;
}

package com.findork.security.feature.symbolAPI;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SymbolApiResponse {
    private String country;
    private String currency;
    private String image;
    private String industry;
}

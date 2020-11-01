package com.findork.security.feature.symbolAPI;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StockResponse {
    private String symbol;
    private String name;
    private String exchange;
}

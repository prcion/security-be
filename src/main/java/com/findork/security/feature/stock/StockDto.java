package com.findork.security.feature.stock;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockDto {
    private Long id;
    private String symbol;
    private String name;
    private String logo;
    private String currency;
    private String country;
    private String exchangeName;
    private String industryName;
}

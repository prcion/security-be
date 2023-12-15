package com.findork.preclinical.features.company.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CompanyDto {
    private String id;
    private String name;
    private Boolean active;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String phone;
}

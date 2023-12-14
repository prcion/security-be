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
    private String website;
    private String billingAttnTo;
    private String billingAddress1;
    private String billingAddress2;
    private String billingCity;
    private String billingState;
    private String billingZip;
    private String billingPhone;
    private String fdaDocumentUrlPrefix;
    private String ein;
    private String countryId;
}

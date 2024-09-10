package com.findork.preclinical.features.administration.site.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SiteDto {
    private String id;
    @NotNull
    private String alias;
    @NotNull
    private String name;
    @NotNull
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String timezone;
}

package com.servustech.skeleton.features.company.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CompanyUpdateRequest {
    @NotNull
    private String name;
    @NotNull
    private String address1;
    private String address2;
    @NotNull
    private String city;
    @NotNull
    private String state;
    @NotNull
    private String zip;
    @NotNull
    private String phone;
    private String website;
    private String billingAttnTo;
    private String billingAddress1;
    private String billingAddress2;
    private String billingCity;
    private String billingState;
    private String billingZip;
    private String billingPhone;
    @NotNull
    private Integer daysUntilUserInviteTokenExpires;
    @NotNull
    private Integer numberOfTimesWhenOldPasswordShouldNotBeUsed;
    @NotNull
    private Integer daysUntilUsersPasswordExpires;
    @NotNull
    private String ein;
    @NotNull
    private String countryId;
}

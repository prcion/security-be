package com.servustech.skeleton.features.company;

import com.servustech.skeleton.features.company.dto.CompanyDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CompanyConverter {

    public CompanyDto fromEntityToDto(Company company) {
        return CompanyDto
                .builder()
                .id(company.getId())
                .name(company.getName())
                .active(company.getActive())
                .address1(company.getAddress1())
                .address2(company.getAddress2())
                .city(company.getCity())
                .state(company.getState())
                .zip(company.getZip())
                .phone(company.getPhone())
                .website(company.getWebsite())
                .billingAttnTo(company.getBillingAttnTo())
                .billingAddress1(company.getBillingAddress1())
                .billingAddress2(company.getBillingAddress2())
                .billingCity(company.getBillingCity())
                .billingState(company.getBillingState())
                .billingZip(company.getBillingZip())
                .billingPhone(company.getBillingPhone())
                .ein(company.getEin())
                .countryId(company.getCountryId())
                .build();
    }
}
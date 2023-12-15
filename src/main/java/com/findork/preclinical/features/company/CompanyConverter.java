package com.findork.preclinical.features.company;

import com.findork.preclinical.features.company.dto.CompanyDto;
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
                .build();
    }
}

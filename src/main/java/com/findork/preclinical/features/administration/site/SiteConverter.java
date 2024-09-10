package com.findork.preclinical.features.administration.site;

import com.findork.preclinical.features.administration.site.dto.SiteDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class SiteConverter {

    public SiteDto fromEntityToDto(Site site) {
        return SiteDto
                .builder()
                .id(site.getId())
                .alias(site.getAlias())
                .name(site.getName())
                .address1(site.getAddress1())
                .address2(site.getAddress2())
                .city(site.getCity())
                .state(site.getState())
                .zip(site.getZip())
                .phoneNumber(site.getPhoneNumber())
                .timezone(site.getTimezone())
                .build();
    }

    public Site fromDtoToEntity(SiteDto request) {
        return Site
                .builder()
                .name(request.getName())
                .alias(request.getAlias())
                .address1(request.getAddress1())
                .address2(request.getAddress2())
                .city(request.getCity())
                .state(request.getState())
                .zip(request.getZip())
                .phoneNumber(request.getPhoneNumber())
                .timezone(request.getTimezone())
                .build();
    }

    public void update(Site site, SiteDto request) {
        site.setName(request.getName());
        site.setAlias(request.getAlias());
        site.setAddress1(request.getAddress1());
        site.setAddress2(request.getAddress2());
        site.setCity(request.getCity());
        site.setState(request.getState());
        site.setZip(request.getZip());
        site.setPhoneNumber(request.getPhoneNumber());
        site.setTimezone(request.getTimezone());
    }
}

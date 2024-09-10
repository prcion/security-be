package com.findork.preclinical.features.administration.site;

import com.findork.preclinical.exceptions.ValidationException;
import com.findork.preclinical.features.administration.account.domain.User;
import com.findork.preclinical.features.administration.company.CompanyService;
import com.findork.preclinical.features.administration.site.dto.SiteDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class SiteService {
    private final SiteRepository siteRepository;
    private final SiteConverter siteConverter;

    public Site create(String companyId, SiteDto request, User user) {
        CompanyService.validateIfUserCanAccessCompany(companyId, user);

        var site = siteConverter.fromDtoToEntity(request);
        site.setActive(true);
        site.setCompanyId(companyId);

        return siteRepository.save(site);
    }

    public List<SiteDto> findAllSiteByCompanyId(String companyId, User user) {
        CompanyService.validateIfUserCanAccessCompany(companyId, user);

        return siteRepository.findAllByCompanyId(companyId)
                .stream()
                .map(siteConverter::fromEntityToDto)
                .toList();
    }

    public Site findByIdOrThrow(String siteId) {
        return siteRepository.findById(siteId)
                .orElseThrow(() -> new ValidationException("Site with given id: " + siteId + " not found."));
    }

    public Site update(String siteId, SiteDto request, User user) {
        var site = findByIdOrThrow(siteId);

        CompanyService.validateIfUserCanAccessCompany(site.getCompanyId(), user);

        siteConverter.update(site, request);

        return siteRepository.save(site);
    }
}

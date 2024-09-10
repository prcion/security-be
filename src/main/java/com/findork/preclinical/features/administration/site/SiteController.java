package com.findork.preclinical.features.administration.site;

import com.findork.preclinical.features.administration.account.domain.User;
import com.findork.preclinical.features.administration.site.dto.SiteDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/sites")
public class SiteController {
    
    private final SiteService siteService;
    private final SiteConverter siteConverter;

    @PostMapping("/companies/{companyId}")
    @PreAuthorize("hasAnyRole('SYSTEM_ADMINISTRATOR', 'COMPANY_ADMINISTRATOR')")
    public SiteDto createSite(@PathVariable String companyId, @RequestBody @Valid SiteDto request, User user) {
        var site = siteService.create(companyId, request, user);
        return siteConverter.fromEntityToDto(site);
    }

    @GetMapping("/companies/{companyId}")
    public List<SiteDto> findAllSiteByCompany(@PathVariable String companyId, User user) {
        return siteService.findAllSiteByCompanyId(companyId, user);
    }

    @PutMapping("/{siteId}")
    @PreAuthorize("hasAnyRole('SYSTEM_ADMINISTRATOR', 'COMPANY_ADMINISTRATOR')")
    public SiteDto updateSite(@PathVariable String siteId, @RequestBody @Valid SiteDto request, User user) {
        var site = siteService.update(siteId, request, user);
        return siteConverter.fromEntityToDto(site);
    }
}

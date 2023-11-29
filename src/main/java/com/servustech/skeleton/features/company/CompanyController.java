package com.servustech.skeleton.features.company;

import com.servustech.skeleton.features.account.User;
import com.servustech.skeleton.features.company.dto.CompanyCreateRequest;
import com.servustech.skeleton.features.company.dto.CompanyDto;
import com.servustech.skeleton.features.company.dto.CompanyUpdateRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/companies")
@AllArgsConstructor
@Log4j2
public class CompanyController {
    private final CompanyService companyService;
    private final CompanyConverter companyConverter;

    @PostMapping
    @PreAuthorize("hasRole('SYSTEM_ADMINISTRATOR')")
    public CompanyDto save(@RequestBody @Valid CompanyCreateRequest request, User user) {
        var company = companyService.save(request, user);
        return companyConverter.fromEntityToDto(company);
    }

    @PutMapping("/{companyId}")
    @PreAuthorize("hasAnyRole('SYSTEM_ADMINISTRATOR', 'COMPANY_ADMINISTRATOR')")
    public CompanyDto update(@PathVariable String companyId, @RequestBody CompanyUpdateRequest request, User user) {
        var company = companyService.update(companyId, request, user);
        return companyConverter.fromEntityToDto(company);
    }

    @GetMapping("/{companyId}")
    public CompanyDto findById(@PathVariable String companyId) {
        var company = companyService.findByIdOrThrow(companyId);
        return companyConverter.fromEntityToDto(company);
    }
}

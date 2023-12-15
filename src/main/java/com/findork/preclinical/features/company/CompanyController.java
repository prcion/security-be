package com.findork.preclinical.features.company;

import com.findork.preclinical.features.account.User;
import com.findork.preclinical.features.company.dto.CompanyCreateRequest;
import com.findork.preclinical.features.company.dto.CompanyDto;
import com.findork.preclinical.features.company.dto.CompanyUpdateRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping
    @PreAuthorize("hasRole('SYSTEM_ADMINISTRATOR')")
    public List<CompanyDto> findAll() {
        var companies = companyService.findAll();
        return companies
                .stream()
                .map(companyConverter::fromEntityToDto)
                .collect(Collectors.toList());
    }
}

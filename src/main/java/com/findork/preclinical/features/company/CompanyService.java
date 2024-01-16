package com.findork.preclinical.features.company;

import com.findork.preclinical.exceptions.NotFoundException;
import com.findork.preclinical.exceptions.PermissionDeniedException;
import com.findork.preclinical.features.account.domain.User;
import com.findork.preclinical.features.commons.SecurityUtils;
import com.findork.preclinical.features.company.dto.CompanyCreateRequest;
import com.findork.preclinical.features.company.dto.CompanyUpdateRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public Company save(CompanyCreateRequest request, User user) {
        validateIfUserCanSaveCompany(user);
        var company = Company
                .builder()
                .name(request.getName())
                .active(true)
                .address1(request.getAddress1())
                .address2(request.getAddress2())
                .city(request.getCity())
                .state(request.getState())
                .zip(request.getZip())
                .phone(request.getPhone())
                .build();

        return companyRepository.save(company);
    }

    public void validateIfUserCanSaveCompany(User user) {
        if (!user.getAccountType().isSystemAdministrator()) {
            throw new PermissionDeniedException("You're not allowed to do this");
        }
    }

    public static void validateIfUserCanAccessCompany(String companyId, User user) {
        if (!user.getAccountType().isSystemAdministrator()) {
            if (!companyId.equals(user.getCompanyId())){
                throw new PermissionDeniedException("You're not allowed to do this");
            }
        }
    }

    public Company findByIdOrThrow(String companyId) {
        var authenticatedPrincipal = SecurityUtils.getAuthenticatedPrincipal();
        validateIfUserCanAccessCompany(companyId, authenticatedPrincipal);

        return companyRepository.findById(companyId)
                .orElseThrow(() -> new NotFoundException("Company with given id " + companyId + " not found"));
    }

    public Company update(String companyId, CompanyUpdateRequest request, User user) {
        validateIfUserCanAccessCompany(companyId, user);

        var company = findByIdOrThrow(companyId);

        company.setName(request.getName());
        company.setAddress1(request.getAddress1());
        company.setAddress2(request.getAddress2());
        company.setCity(request.getCity());
        company.setState(request.getState());
        company.setZip(request.getZip());
        company.setPhone(request.getPhone());

        return companyRepository.save(company);
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }
}

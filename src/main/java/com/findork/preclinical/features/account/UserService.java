package com.findork.preclinical.features.account;

import com.findork.preclinical.features.account.domain.User;
import com.findork.preclinical.features.company.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public List<User> findAllByCompanyId(String companyId, User user) {
        CompanyService.validateIfUserCanAccessCompany(companyId, user);
        return userRepository.findAllByCompanyId(companyId);
    }
}

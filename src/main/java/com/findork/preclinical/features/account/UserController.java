package com.findork.preclinical.features.account;

import com.findork.preclinical.aop.Audit;
import com.findork.preclinical.features.account.domain.User;
import com.findork.preclinical.features.account.dto.UserAdministrationResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;
    private final UserConverter userConverter;

    @GetMapping("/{userId}/{companyId}/{siteId}")
    @Audit(message = "message here")
    public String getCurrentUser(@PathVariable String userId, @PathVariable String companyId, @PathVariable String siteId) {
//        System.out.println(user);
        return "Congratulation User you can access this api";
    }

    @GetMapping("/companies/{companyId}")
    @PreAuthorize("hasAnyRole('SYSTEM_ADMINISTRATOR', 'COMPANY_ADMINISTRATOR')")
    public List<UserAdministrationResponse> findAllByCompanyId(@PathVariable String companyId, User user) {
        var users = userService.findAllByCompanyId(companyId, user);
        return users
                .stream()
                .map(userConverter::fromEntityToAdministrationResponse)
                .collect(Collectors.toList());
    }
}

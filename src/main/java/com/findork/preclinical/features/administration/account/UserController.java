package com.findork.preclinical.features.administration.account;

import com.findork.preclinical.features.administration.account.domain.User;
import com.findork.preclinical.features.administration.account.dto.UserAdministrationResponse;
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

    @GetMapping("/companies/{companyId}")
    @PreAuthorize("hasAnyRole('SYSTEM_ADMINISTRATOR', 'COMPANY_ADMINISTRATOR')")
    public List<UserAdministrationResponse> findAllByCompanyId(@PathVariable String companyId, User user) {
        var users = userService.findAllByCompanyId(companyId, user);
        return users
                .stream()
                .map(userConverter::fromEntityToAdministrationResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAnyRole('SYSTEM_ADMINISTRATOR', 'COMPANY_ADMINISTRATOR')")
    public UserAdministrationResponse findOneById(@PathVariable String userId) {
        var user = userService.findByIdOrThrow(userId);
        return userConverter.fromEntityToAdministrationResponse(user);
    }
}

package com.findork.preclinical.features.account.permissions;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/permissions")
public class PermissionController {
    private final PermissionService permissionService;

    @PostMapping
    @PreAuthorize("hasAnyRole('SYSTEM_ADMINISTRATOR')")
    public void createPermission(@RequestBody @Valid PermissionDto request) {
        permissionService.createPermission(request);
    }
}

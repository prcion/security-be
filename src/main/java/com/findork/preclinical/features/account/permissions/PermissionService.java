package com.findork.preclinical.features.account.permissions;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PermissionService {
    private final PermissionRepository permissionRepository;


    public void createPermission(PermissionDto request) {
        var permissionOptional = permissionRepository.findByName(request.getName());
        if (permissionOptional.isPresent()) return;

        var permission = Permission
                .builder()
                .name(request.getName())
                .build();

        permissionRepository.save(permission);
    }
}

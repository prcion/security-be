package com.findork.preclinical.features.account.users_permissions;

import com.findork.preclinical.features.account.permissions.PermissionType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserPermissionRequest {
    @NotBlank
    private String permissionId;
    private PermissionType permissionType;
}

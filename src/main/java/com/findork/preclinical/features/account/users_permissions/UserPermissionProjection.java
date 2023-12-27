package com.findork.preclinical.features.account.users_permissions;

import com.findork.preclinical.features.account.permissions.PermissionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserPermissionProjection {
    @Field(value = "permission_id", targetType = FieldType.OBJECT_ID)
    private String permissionId;

    @Field(value = "user_id", targetType = FieldType.OBJECT_ID)
    private String userId;

    private String permissionName;

    private String permissionType;


    public PermissionType getPermissionType() {
        if (permissionType == null) return PermissionType.NONE;
        return PermissionType.valueOf(permissionType);
    }
}

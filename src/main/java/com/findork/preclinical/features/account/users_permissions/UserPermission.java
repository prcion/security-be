package com.findork.preclinical.features.account.users_permissions;

import com.findork.preclinical.features.account.permissions.PermissionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(collection = "users_permissions")
public class UserPermission {
    @Id
    private String id;

    @Field(name = "user_id", targetType = FieldType.OBJECT_ID)
    private String userId;

    @Field(name = "permission_id", targetType = FieldType.OBJECT_ID)
    private String permissionId;

    private PermissionType permissionType;
}

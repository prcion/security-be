package com.findork.preclinical.features.account.users_permissions;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserPermissionRepository extends MongoRepository<UserPermission, String> {
    Optional<UserPermission> findOneByUserIdAndPermissionId(String userId, String permissionId);
}

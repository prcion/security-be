package com.findork.preclinical.features.account.users_permissions;

import com.findork.preclinical.exceptions.NotFoundException;
import com.findork.preclinical.features.account.permissions.PermissionRepository;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserPermissionService {
    private final PermissionRepository permissionRepository;
    private final UserPermissionRepository userPermissionRepository;


    public List<UserPermissionProjection> findAllByUser(String userId) {
        return permissionRepository.findAllPermissionsByUser(new ObjectId(userId));
    }

    public void update(String userId, List<UserPermissionRequest> request) {
        for (var userPermissionRequest : request) {
            var permission = permissionRepository.findById(userPermissionRequest.getPermissionId());
            if (permission.isEmpty()) throw new NotFoundException("Permission with given id " + userPermissionRequest.getPermissionId() + " not found.");

            var userPermissionOptional = userPermissionRepository.findOneByUserIdAndPermissionId(userId, userPermissionRequest.getPermissionId());
            UserPermission userPermission;
            if (userPermissionOptional.isPresent()) {
                userPermission = userPermissionOptional.get();
                userPermission.setPermissionType(userPermissionRequest.getPermissionType());
            } else {
                userPermission = UserPermission
                        .builder()
                        .userId(userId)
                        .permissionId(userPermissionRequest.getPermissionId())
                        .permissionType(userPermissionRequest.getPermissionType())
                        .build();
            }
            userPermissionRepository.save(userPermission);
        }
    }
}

package com.findork.preclinical.features.account.users_permissions;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/users/{userId}/permissions")
public class UserPermissionController {
    private final UserPermissionService userPermissionService;

    @GetMapping
    public List<UserPermissionProjection> findAllByUser(@PathVariable String userId) {
        return userPermissionService.findAllByUser(userId);
    }

    @PutMapping
    public void update(@PathVariable String userId, @RequestBody @Valid List<UserPermissionRequest> request) {
        userPermissionService.update(userId, request);
    }
}

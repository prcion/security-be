package com.servustech.skeleton.features.account;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public String getCurrentUser(User user) {
        System.out.println(user);
        return "Congratulation User you can access this api";
    }
}

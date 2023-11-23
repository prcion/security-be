package com.servustech.skeleton.features.account;

import com.servustech.skeleton.security.payload.UserRequest;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String createPoll(@Valid @RequestBody UserRequest userRequest) {
       return "Congratulation Admin you can access this api";
    }

}

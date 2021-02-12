package com.servustech.skeleton.feature.account.mapper;

import com.servustech.skeleton.feature.account.AccountStatus;
import com.servustech.skeleton.feature.account.User;
import com.servustech.skeleton.feature.account.role.RoleService;
import com.servustech.skeleton.security.payload.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

public abstract class UserMapperDecorator implements UserMapper {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleService roleService;


    @Override
    public User signUpRequestToUser(SignUpRequest signUpRequest) {
        User user = userMapper.signUpRequestToUser(signUpRequest);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setAccountStatus(AccountStatus.ACTIVE);
        user.setRoles(Collections.singleton(roleService.getUserRole()));
        return user;
    }
}

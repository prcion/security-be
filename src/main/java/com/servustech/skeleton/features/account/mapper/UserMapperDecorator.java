package com.servustech.skeleton.features.account.mapper;

import com.servustech.skeleton.features.account.AccountStatus;
import com.servustech.skeleton.features.account.User;
import com.servustech.skeleton.security.payload.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public abstract class UserMapperDecorator implements UserMapper {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public User signUpRequestToUser(RegisterRequest registerRequest) {
        User user = userMapper.signUpRequestToUser(registerRequest);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setAccountStatus(AccountStatus.INACTIVE);
        user.setRole("USER");
        return user;
    }
}

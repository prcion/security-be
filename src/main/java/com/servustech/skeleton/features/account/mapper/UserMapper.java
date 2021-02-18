package com.servustech.skeleton.features.account.mapper;

import com.servustech.skeleton.features.account.User;
import com.servustech.skeleton.security.payload.SignUpRequest;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper
@DecoratedWith(UserMapperDecorator.class)
public interface UserMapper {

    User signUpRequestToUser(SignUpRequest signUpRequest);

}

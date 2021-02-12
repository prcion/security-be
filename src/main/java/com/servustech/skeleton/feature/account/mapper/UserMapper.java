package com.servustech.skeleton.feature.account.mapper;

import com.servustech.skeleton.feature.account.User;
import com.servustech.skeleton.security.payload.SignUpRequest;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper
@DecoratedWith(UserMapperDecorator.class)
public interface UserMapper {

    User signUpRequestToUser(SignUpRequest signUpRequest);

}

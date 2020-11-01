package com.findork.security.feature.account.mapper;

import com.findork.security.feature.account.User;
import com.findork.security.security.payload.SignUpRequest;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper
@DecoratedWith(UserMapperDecorator.class)
public interface UserMapper {

    User signUpRequestToUser(SignUpRequest signUpRequest);

}

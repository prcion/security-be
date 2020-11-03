package com.findork.chiriezerie.feature.account.mapper;

import com.findork.chiriezerie.feature.account.User;
import com.findork.chiriezerie.security.payload.SignUpRequest;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper
@DecoratedWith(UserMapperDecorator.class)
public interface UserMapper {

    User signUpRequestToUser(SignUpRequest signUpRequest);

}

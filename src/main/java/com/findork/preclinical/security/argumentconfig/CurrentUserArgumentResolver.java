package com.findork.preclinical.security.argumentconfig;

import com.findork.preclinical.security.userdetails.UserPrincipal;
import com.findork.preclinical.features.account.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@Slf4j
@AllArgsConstructor
public class CurrentUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(@NonNull MethodParameter parameter) {
        return is(parameter, User.class);
    }

    @Override
    public Object resolveArgument(@NonNull MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  @NonNull NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) {

        var context = SecurityContextHolder.getContext();
        var authentication = context.getAuthentication();
        if (authentication.getPrincipal() instanceof UserPrincipal) { // logged in / user has token
            var principal = (UserPrincipal) authentication.getPrincipal();
            if (is(parameter, User.class)) {
                return principal.getUser();
            } else if (is(parameter, UserPrincipal.class)) {
                return principal;
            }
            throw new RuntimeException("Invalid parameter");
        }
        // we want to inject account/jwtuserprincipal but the token was not set in jwtFilter because the user is anonymous
        // and doesn't have one
        return null;
    }

    private boolean is(MethodParameter parameter, Class aClass) {
        return parameter.getParameterType().equals(aClass);
    }
}

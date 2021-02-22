package com.servustech.skeleton.utils;

import com.servustech.skeleton.exception.CustomException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
public class PasswordEncoderUtils {

    private static final BCryptPasswordEncoder passwordEncoder;


    public static void verifyMatchingPasswords(String oldPassword, String currentPassword) {

        if (!passwordEncoder.matches(oldPassword, currentPassword)) {
            throw new CustomException("Invalid old password");
        }

    }

    public static String encode(String password){
        return passwordEncoder.encode(password);
    }

}

package com.servustech.skeleton.utils;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Class used to generate a registration token which will be sent by email for e.g. to the user.
 *
 * @author Arpad Sebesi
 */
public class TokenUtils {

    private static final int TOKEN_LENGTH = 8;
    private static final int PASSWORD_LENGTH = 6;

    public static String generateConfirmationToken() {
        return RandomStringUtils.randomAlphanumeric(TOKEN_LENGTH);
    }

    public static String generateRandomPassword() {
        return RandomStringUtils.randomAlphanumeric(PASSWORD_LENGTH);
    }
}

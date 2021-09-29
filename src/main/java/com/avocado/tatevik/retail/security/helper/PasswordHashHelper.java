package com.avocado.tatevik.retail.security.helper;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordHashHelper {

    private static final int LOG_ROUNDS = 12;

    public static Boolean isPasswordCorrect(final String hashPassword) {
        return BCrypt.checkpw(hashPassword, hashPassword(hashPassword));
    }

    public static String hashPassword(final String hashPassword) {
        String salt = BCrypt.gensalt(LOG_ROUNDS);
        return BCrypt.hashpw(hashPassword, salt);
    }
}

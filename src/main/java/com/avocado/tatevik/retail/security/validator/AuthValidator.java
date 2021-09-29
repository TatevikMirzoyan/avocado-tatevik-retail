package com.avocado.tatevik.retail.security.validator;

import com.avocado.tatevik.retail.repository.token.entity.TokenEntity;
import com.avocado.tatevik.retail.security.helper.PasswordHashHelper;
import com.avocado.tatevik.retail.security.auth.AuthRequest;
import com.avocado.tatevik.retail.security.jwt.model.JwtUserDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static org.springframework.util.Assert.hasText;
import static org.springframework.util.Assert.notNull;

@Component
public class AuthValidator {

    private final Logger logger = LoggerFactory.getLogger(AuthValidator.class);

    public void validate(AuthRequest request) {
        notNull(request, "AuthRequest.request cannot be null.");
        notNull(request.getUsername(), "AuthRequest.request.username cannot be null.");
        notNull(request.getPassword(), "AuthRequest.request.password cannot be null.");
    }

    public void validate(final JwtUserDetail userDetail) {
        notNull(userDetail, "userDetail can not be null");
        final Long userId = userDetail.getJwtUserDto().getId();
        notNull(userId, "userDetail.userId can not be null or empty.");
        final String username = userDetail.getUsername();
        hasText(username, "userDetail.username can not be null or empty.");
        final String hashPassword = userDetail.getPassword();
        hasText(hashPassword, "userDetail.hashPassword can not be null or empty.");
        logger.debug("Attempting authentication with password for user: '{}'...", userId);
        if (!PasswordHashHelper.isPasswordCorrect(hashPassword)) {
            logger.debug("Password validation failed for user: '{}'.", userId);
            throw new RuntimeException(String.format("Password validation failed for user: '%s'.", userId));
        }
        logger.info("Password validation passed for user: '{}'.", userId);
    }

    public boolean isExpiredLoginToken(TokenEntity token) {
        return (isExpired(token.getExpires()) || !token.getActive() || token.isDeleted());
    }

    private boolean isExpired(final LocalDateTime expirationTime) {
        return expirationTime.isBefore(LocalDateTime.now());
    }
}

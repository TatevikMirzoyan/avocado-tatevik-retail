package com.avocado.tatevik.retail.security.facade;

import com.avocado.tatevik.retail.common.exception.auth.AuthenticationTokenExpiredException;
import com.avocado.tatevik.retail.common.exception.auth.AuthenticationTokenMissingException;
import com.avocado.tatevik.retail.common.exception.response.ExceptionCode;
import com.avocado.tatevik.retail.repository.token.entity.TokenEntity;
import com.avocado.tatevik.retail.security.service.JwtUserDetailService;
import com.avocado.tatevik.retail.security.auth.JwtAuthenticationResponse;
import com.avocado.tatevik.retail.security.jwt.converter.JwtTokenModelConverter;
import com.avocado.tatevik.retail.security.auth.AuthRequest;
import com.avocado.tatevik.retail.security.auth.AuthResponse;
import com.avocado.tatevik.retail.security.jwt.model.JwtTokenRequest;
import com.avocado.tatevik.retail.security.jwt.model.JwtUserDetail;
import com.avocado.tatevik.retail.security.validator.AuthValidator;
import com.avocado.tatevik.retail.service.token.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static org.springframework.util.Assert.hasText;
import static org.springframework.util.Assert.notNull;

@Service
public class AuthenticationFacade implements AuthenticationManager {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationFacade.class);

    @Autowired
    private JwtUserDetailService jwtUserDetailService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthValidator authValidator;

    @Autowired
    private JwtTokenModelConverter jwtTokenModelConverter;

    public AuthResponse authenticateByCredentials(AuthRequest request) {
        authValidator.validate(request);
        JwtUserDetail userDetail = jwtUserDetailService.loadUserByUsernameAndPassword(request.getUsername(), request.getPassword());
        logoutFromAllDevices(userDetail.getJwtUserDto().getId());
        authValidator.validate(userDetail);
        final TokenEntity apiAuthAccessToken = tokenService.createJwtToken(jwtTokenModelConverter.convert(userDetail));
        return new AuthResponse(userDetail, apiAuthAccessToken.getToken());
    }

    @Override
    public Authentication authenticate(final Authentication authentication) {
        return new JwtAuthenticationResponse(authenticateByCredentials((AuthRequest) authentication.getDetails()));
    }

    public void logout(final String token) {
        hasText(token, "token can not be null or empty.");
        logoutFromAllDevices(token);
    }

    public void logoutFromAllDevices(final String token) {
        hasText(token, "token can not be null or empty.");
        tokenService.findByToken(token).ifPresent(existingToken -> logoutFromAllDevices(existingToken.getUserId()));
    }

    public void logoutFromAllDevices(final Long userId) {
        notNull(userId, "User ID can not be null or empty.");
        final List<TokenEntity> existingTokens = tokenService.findAllByUserId(userId);
        if (!CollectionUtils.isEmpty(existingTokens)) {
           //here deletes the token
            tokenService.deleteAllToken(userId);
            logger.info("LOGGED OUT partner:'{}'.", userId);
        }
    }

    public AuthResponse authenticateApiAccessToken(final String token) throws AuthenticationTokenExpiredException, AuthenticationTokenMissingException {
        TokenEntity existingToken = tokenService.findByToken(token).orElse(null);
        if (existingToken == null) {
            throw new AuthenticationTokenMissingException(ExceptionCode.UUTI_45, "Authentication token doesn't exist. Please sign in.");
        }
        if (authValidator.isExpiredLoginToken(existingToken)) {
            tokenService.deleteToken(new JwtTokenRequest(existingToken));
            logoutFromAllDevices(existingToken.getUserId());
            throw new AuthenticationTokenExpiredException(ExceptionCode.UUTI_45, "Authentication token is expired");
        }
        return new AuthResponse(jwtUserDetailService.loadUserByUsername(existingToken.getUsername()), existingToken.getToken());
    }
}

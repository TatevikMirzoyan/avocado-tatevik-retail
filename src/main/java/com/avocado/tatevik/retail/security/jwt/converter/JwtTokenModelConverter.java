package com.avocado.tatevik.retail.security.jwt.converter;

import com.avocado.tatevik.retail.common.enums.TokenType;
import com.avocado.tatevik.retail.repository.token.entity.TokenEntity;
import com.avocado.tatevik.retail.security.jwt.model.JwtTokenCreationRequest;
import com.avocado.tatevik.retail.security.jwt.model.JwtUserDetail;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class JwtTokenModelConverter {

    public JwtTokenCreationRequest convert(final JwtUserDetail userDetail) {
        final JwtTokenCreationRequest apiAuthAccessTokenCreationRequest = new JwtTokenCreationRequest();
        apiAuthAccessTokenCreationRequest.setUserDetail(userDetail);
        apiAuthAccessTokenCreationRequest.setTokenType(TokenType.LOGIN);
        apiAuthAccessTokenCreationRequest.setActive(true);
        apiAuthAccessTokenCreationRequest.setExpires(LocalDateTime.now().plusMinutes(30));
        return apiAuthAccessTokenCreationRequest;
    }

    public TokenEntity convert(JwtTokenCreationRequest request, String token) {
        if (request == null) {
            return null;
        }
        final TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setToken(token);
        tokenEntity.setTokenType(request.getTokenType());
        tokenEntity.setActive(true);
        tokenEntity.setUserId(request.getUserDetail().getJwtUserDto().getId());
        tokenEntity.setUsername(request.getUserDetail().getUsername());
        tokenEntity.setExpires(request.getExpires());
        return tokenEntity;
    }
}

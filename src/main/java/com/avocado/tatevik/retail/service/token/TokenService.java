package com.avocado.tatevik.retail.service.token;

import com.avocado.tatevik.retail.common.enums.TokenType;
import com.avocado.tatevik.retail.repository.token.TokenRepository;
import com.avocado.tatevik.retail.repository.token.entity.TokenEntity;
import com.avocado.tatevik.retail.security.jwt.converter.JwtTokenModelConverter;
import com.avocado.tatevik.retail.security.jwt.model.JwtTokenCreationRequest;
import com.avocado.tatevik.retail.security.jwt.model.JwtTokenRequest;
import com.avocado.tatevik.retail.security.service.JwtTokenUtil;
import com.avocado.tatevik.retail.security.jwt.model.JwtUserDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.util.Assert.hasText;
import static org.springframework.util.Assert.notNull;

@Service
public class TokenService {

    private final Logger logger = LoggerFactory.getLogger(TokenService.class);

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private JwtTokenModelConverter jwtTokenModelConverter;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Transactional
    public TokenEntity createJwtToken(final JwtTokenCreationRequest request) {
        notNull(request, "request can not be null");
        final JwtUserDetail userDetail = request.getUserDetail();
        notNull(userDetail, "request.userDetail can not be null");
        final TokenType tokenType = request.getTokenType();
        notNull(tokenType, "request.tokenType can not be null");

        final Long userId = userDetail.getJwtUserDto().getId();
        logger.debug("Creating JwtToken for userDetail:'{}'...", userId);
        final String token = jwtTokenUtil.generateToken(userDetail);

        Date date = jwtTokenUtil.getExpirationDate(token);
        final LocalDateTime expires = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        request.setExpires(expires.plusMinutes(30));
        deleteAllToken(userId);
        tokenRepository.deleteAllByToken(token);
        final TokenEntity tokenEntity = tokenRepository.save(jwtTokenModelConverter.convert(request, token));
        logger.info("TokenEntity:'{}' is created for user:'{}'.", tokenEntity.getToken(), userId);
        return tokenEntity;
    }

    public Optional<TokenEntity> findByToken(String token) {
        hasText(token, "token can not be null");
        return Optional.ofNullable(tokenRepository.findByToken(token));
    }

    public List<TokenEntity> findAllByUserId(Long userId) {
        notNull(userId, "userId can not be null");
        return tokenRepository.findAllByUserId(userId);
    }

    public void deleteAllToken(Long userId) {
        notNull(userId, "userId can not be null");
        tokenRepository.deleteAllByUserId(userId);
    }

    public void deleteToken(JwtTokenRequest request) {
        notNull(request, "request can not be null");
        final TokenEntity tokenEntity = request.getToken();
        notNull(tokenEntity, "request.TokenEntity can not be null");
        tokenRepository.delete(tokenEntity);
    }
}

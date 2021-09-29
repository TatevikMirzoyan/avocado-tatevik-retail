package com.avocado.tatevik.retail.service.token.converter;

import com.avocado.tatevik.retail.repository.token.entity.TokenEntity;
import com.avocado.tatevik.retail.service.token.model.TokenModel;
import org.springframework.stereotype.Component;

@Component
public class TokenEntityConverter {

    public TokenModel convert(TokenEntity entity) {
        if (entity == null) {
            return null;
        }
        return new TokenModel(
                entity.getId(),
                entity.getUserId(),
                entity.getUsername(),
                entity.getToken(),
                entity.getTokenType(),
                entity.getExpires(),
                entity.getActive());
    }
}

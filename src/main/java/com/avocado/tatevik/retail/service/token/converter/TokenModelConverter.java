package com.avocado.tatevik.retail.service.token.converter;

import com.avocado.tatevik.retail.repository.token.entity.TokenEntity;
import com.avocado.tatevik.retail.service.token.model.TokenCreationModel;
import org.springframework.stereotype.Component;

@Component
public class TokenModelConverter {

    public TokenEntity convert(TokenCreationModel model) {
        if (model == null) {
            return null;
        }
        TokenEntity entity = new TokenEntity();
        entity.setUserId(model.getUserId());
        entity.setUsername(model.getUsername());
        entity.setToken(model.getToken());
        entity.setTokenType(model.getTokenType());
        entity.setExpires(model.getExpires());
        entity.setActive(model.getActive());
        return entity;
    }
}

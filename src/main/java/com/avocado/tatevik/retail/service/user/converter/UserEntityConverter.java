package com.avocado.tatevik.retail.service.user.converter;

import com.avocado.tatevik.retail.repository.user.entity.UserEntity;
import com.avocado.tatevik.retail.service.user.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserEntityConverter {

    public UserModel convert(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        return new UserModel(
                entity.getId(),
                entity.getUsername(),
                entity.getPasswordHash(),
                entity.getEmail(),
                entity.getRoles());
    }
}

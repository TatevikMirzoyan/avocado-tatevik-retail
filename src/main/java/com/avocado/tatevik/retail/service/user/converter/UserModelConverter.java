package com.avocado.tatevik.retail.service.user.converter;

import com.avocado.tatevik.retail.controller.user.dto.UserDto;
import com.avocado.tatevik.retail.repository.user.entity.UserEntity;
import com.avocado.tatevik.retail.service.user.model.UserCreationModel;
import com.avocado.tatevik.retail.service.user.model.UserModel;
import com.avocado.tatevik.retail.service.user.model.UserUpdateModel;
import org.springframework.stereotype.Component;

@Component
public class UserModelConverter {

    public UserDto convert(UserModel model) {
        if (model == null) {
            return null;
        }
        return new UserDto(
                model.getId(),
                model.getUsername(),
                model.getPassword(),
                model.getEmail(),
                model.getRoles());
    }

    public UserEntity convert(UserUpdateModel model, UserEntity entity) {
        if (model == null) {
            return null;
        }
        entity.setUsername(model.getUsername());
        entity.setPasswordHash(model.getPassword());
        entity.setEmail(model.getEmail());
        entity.setRoles(model.getRoles());
        return entity;
    }

    public UserEntity convert(UserCreationModel model) {
        if (model == null) {
            return null;
        }
        UserEntity entity = new UserEntity();
        entity.setUsername(model.getUsername());
        entity.setPasswordHash(model.getPassword());
        entity.setEmail(model.getEmail());
        entity.setRoles(model.getRoles());
        return entity;
    }
}

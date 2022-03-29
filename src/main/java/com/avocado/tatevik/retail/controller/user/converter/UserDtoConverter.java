package com.avocado.tatevik.retail.controller.user.converter;

import com.avocado.tatevik.retail.controller.user.dto.UserCreationDto;
import com.avocado.tatevik.retail.controller.user.dto.UserDto;
import com.avocado.tatevik.retail.controller.user.dto.UserUpdateDto;
import com.avocado.tatevik.retail.service.user.model.UserCreationModel;
import com.avocado.tatevik.retail.service.user.model.UserModel;
import com.avocado.tatevik.retail.service.user.model.UserUpdateModel;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {

    public UserModel convert(UserDto dto) {
        if (dto == null) {
            return null;
        }
        return new UserModel(
                dto.getId(),
                dto.getUsername(),
                dto.getPasswordHash(),
                dto.getEmail(),
                dto.getRoles());
    }

    public UserUpdateModel convert(Long id, UserUpdateDto dto) {
        if (dto == null) {
            return null;
        }
        return new UserUpdateModel(
                id,
                dto.getUsername(),
                dto.getPassword(),
                dto.getEmail(),
                dto.getRoles());
    }

    public UserCreationModel convert(UserCreationDto dto) {
        if (dto == null) {
            return null;
        }
        return new UserCreationModel(
                dto.getUsername(),
                dto.getPassword(),
                dto.getEmail(),
                dto.getRoles());
    }
}

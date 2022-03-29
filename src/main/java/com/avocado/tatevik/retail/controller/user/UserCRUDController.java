package com.avocado.tatevik.retail.controller.user;

import com.avocado.tatevik.retail.common.exception.response.ErrorResponseListDto;
import com.avocado.tatevik.retail.common.exception.response.GenericResponse;
import com.avocado.tatevik.retail.controller.user.converter.UserDtoConverter;
import com.avocado.tatevik.retail.controller.user.dto.UserCreationDto;
import com.avocado.tatevik.retail.controller.user.dto.UserDto;
import com.avocado.tatevik.retail.controller.user.dto.UserUpdateDto;
import com.avocado.tatevik.retail.service.user.UserService;
import com.avocado.tatevik.retail.service.user.converter.UserModelConverter;
import com.avocado.tatevik.retail.service.user.model.UserCreationModel;
import com.avocado.tatevik.retail.service.user.model.UserModel;
import com.avocado.tatevik.retail.service.user.model.UserUpdateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserCRUDController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserModelConverter userModelConverter;

    @Autowired
    private UserDtoConverter userDtoConverter;

    @GetMapping("/{id}")
    public GenericResponse<UserDto> get(@PathVariable Long id) {
        UserModel model = userService.get(id);
        UserDto dto = userModelConverter.convert(model);
        return new GenericResponse<>(dto, new ErrorResponseListDto());
    }

    @PostMapping
    public GenericResponse<UserDto> create(@Valid @RequestBody UserCreationDto creationDto) {
        UserCreationModel creationModel = userDtoConverter.convert(creationDto);
        UserModel model = userService.create(creationModel);
        UserDto dto = userModelConverter.convert(model);
        return new GenericResponse<>(dto, new ErrorResponseListDto());
    }

    @PutMapping("/{id}")
    public GenericResponse<UserDto> update(@PathVariable Long id, @Valid @RequestBody UserUpdateDto updateDto) {
        UserUpdateModel updateModel = userDtoConverter.convert(id, updateDto);
        UserModel model = userService.update(updateModel);
        UserDto dto = userModelConverter.convert(model);
        return new GenericResponse<>(dto, new ErrorResponseListDto());
    }

    @DeleteMapping("/{id}")
    public GenericResponse<Boolean> delete(@PathVariable Long id) {
        Boolean isDeleted = userService.delete(id);
        return new GenericResponse<>(isDeleted, new ErrorResponseListDto());
    }
}

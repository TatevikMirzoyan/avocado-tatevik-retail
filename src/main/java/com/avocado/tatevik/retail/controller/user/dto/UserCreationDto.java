package com.avocado.tatevik.retail.controller.user.dto;

import com.avocado.tatevik.retail.repository.role.entity.RoleEntity;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UserCreationDto {

    @NotBlank(message = "User username can not be null")
    private String username;

    @NotBlank(message = "User password can not be null")
    private String password;

    @NotBlank(message = "User email can not be null")
    private String email;

    private List<RoleEntity> roles;
}

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
public class UserUpdateDto {

    private Long id;

    @NotBlank(message = "username could not be null")
    private String username;

    @NotBlank(message = "password could not be null")
    private String password;

    @NotBlank(message = "email could not be null")
    private String email;

    private List<RoleEntity> roles;
}

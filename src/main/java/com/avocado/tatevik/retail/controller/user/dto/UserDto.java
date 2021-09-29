package com.avocado.tatevik.retail.controller.user.dto;

import com.avocado.tatevik.retail.repository.role.entity.RoleEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    private String username;

    private String passwordHash;

    private String email;

    private List<RoleEntity> roles;
}

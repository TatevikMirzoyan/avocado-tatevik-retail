package com.avocado.tatevik.retail.service.user.model;

import com.avocado.tatevik.retail.repository.role.entity.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class UserModel {

    private Long id;

    private String username;

    private String password;

    private String email;

    private List<RoleEntity> roles;
}

package com.avocado.tatevik.retail.repository.role.entity;

import com.avocado.tatevik.retail.common.enums.RoleType;
import com.avocado.tatevik.retail.repository.AbstractEntity;
import com.avocado.tatevik.retail.repository.permission.entity.Permission;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity(name = "RoleEntity")
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RoleEntity extends AbstractEntity implements GrantedAuthority {

    @Id
    @GeneratedValue(generator = "role_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "role_generator", sequenceName = "role_seq")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private RoleType role;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Permission> permissions;

    @Override
    public String getAuthority() {
        return role.name();
    }
}

package com.avocado.tatevik.retail.repository.permission.entity;


import com.avocado.tatevik.retail.common.enums.PermissionType;
import com.avocado.tatevik.retail.repository.AbstractEntity;
import com.avocado.tatevik.retail.repository.role.entity.RoleEntity;
import lombok.*;

import javax.persistence.*;

@Entity(name = "Permission")
@Table(name = "permission")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Permission extends AbstractEntity {

    @Id
    @GeneratedValue(generator = "permission_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "permission_generator", sequenceName = "permission_seq")
    private Long id;

    @Column(name = "type", nullable = false, unique = true)
    @Enumerated(value = EnumType.STRING)
    private PermissionType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private RoleEntity role;
}

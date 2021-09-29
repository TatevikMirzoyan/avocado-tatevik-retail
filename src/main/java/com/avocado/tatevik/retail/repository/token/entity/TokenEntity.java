package com.avocado.tatevik.retail.repository.token.entity;

import com.avocado.tatevik.retail.common.enums.TokenType;
import com.avocado.tatevik.retail.repository.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "TokenEntity")
@Table(name = "token")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TokenEntity extends AbstractEntity {

    @Id
    @GeneratedValue(generator = "token_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "token_generator", sequenceName = "token_seq")
    private Long id;

    @Column(name = "userId", nullable = false)
    private Long userId;

    @Column
    private String username;

    @Column(nullable = false, unique = true, length = 1000)
    private String token;

    @Column(name = "token_type")
    @Enumerated(value = EnumType.STRING)
    private TokenType tokenType;

    @Column
    private LocalDateTime expires;

    @Column(nullable = false)
    private Boolean active;
}

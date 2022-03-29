package com.avocado.tatevik.retail.service.token.model;

import com.avocado.tatevik.retail.common.enums.TokenType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class TokenModel {

    private Long id;

    private Long userId;

    private String username;

    private String token;

    private TokenType tokenType;

    private LocalDateTime expires;

    private Boolean active;
}

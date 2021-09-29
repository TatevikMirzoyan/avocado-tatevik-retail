package com.avocado.tatevik.retail.service.token.model;

import com.avocado.tatevik.retail.common.enums.TokenType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class TokenCreationModel {

    private Long userId;

    private String username;

    private String token;

    private TokenType tokenType;

    private LocalDateTime expires;

    private Boolean active;
}

package com.avocado.tatevik.retail.security.jwt.model;

import com.avocado.tatevik.retail.common.enums.TokenType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtTokenCreationRequest {

    private JwtUserDetail userDetail;

    private TokenType tokenType;

    private boolean isActive;

    private LocalDateTime expires;
}

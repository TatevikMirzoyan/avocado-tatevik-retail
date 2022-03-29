package com.avocado.tatevik.retail.security.jwt.model;

import com.avocado.tatevik.retail.common.enums.TokenType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

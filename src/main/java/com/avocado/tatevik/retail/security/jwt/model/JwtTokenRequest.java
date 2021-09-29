package com.avocado.tatevik.retail.security.jwt.model;

import com.avocado.tatevik.retail.repository.token.entity.TokenEntity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtTokenRequest {

    private TokenEntity token;
}

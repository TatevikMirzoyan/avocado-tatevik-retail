package com.avocado.tatevik.retail.security.jwt.model;

import com.avocado.tatevik.retail.repository.token.entity.TokenEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtTokenRequest {

    private TokenEntity token;
}

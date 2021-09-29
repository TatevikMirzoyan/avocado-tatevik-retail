package com.avocado.tatevik.retail.security.auth;

import com.avocado.tatevik.retail.security.jwt.model.JwtUserDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    private JwtUserDetail userDetail;

    private String jwtToken;
}

package com.avocado.tatevik.retail.security.auth;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {

    private String username;

    private String password;
}

package com.avocado.tatevik.retail.security.auth;

import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthenticationResponse implements Authentication {

    private AuthResponse authResponse;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authResponse.getUserDetail().getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return authResponse.getUserDetail().getUsername();
    }

    @Override
    public Object getDetails() {
        return authResponse;
    }

    @Override
    public Object getPrincipal() {
        return authResponse.getUserDetail();
    }

    @Override
    public boolean isAuthenticated() {
        return !StringUtils.isEmpty(authResponse.getJwtToken());
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return authResponse.getUserDetail().getUsername();
    }
}

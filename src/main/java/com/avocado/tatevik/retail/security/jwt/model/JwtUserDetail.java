package com.avocado.tatevik.retail.security.jwt.model;

import com.avocado.tatevik.retail.security.auth.dto.JwtUserDto;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Setter
public class JwtUserDetail implements UserDetails {

    private JwtUserDto jwtUserDto;

    private String passwordHash;

    private Collection<? extends GrantedAuthority> authorities;

    public JwtUserDetail() {
        if (jwtUserDto == null) {
            this.jwtUserDto = new JwtUserDto();
        }
    }

    public JwtUserDto getJwtUserDto() {
        return jwtUserDto;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities == null) {
            authorities = new ArrayList<>();
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public String getUsername() {
        return jwtUserDto.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

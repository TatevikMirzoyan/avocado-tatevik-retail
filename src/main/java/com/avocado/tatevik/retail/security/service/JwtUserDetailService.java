package com.avocado.tatevik.retail.security.service;

import com.avocado.tatevik.retail.repository.user.UserRepository;
import com.avocado.tatevik.retail.repository.user.entity.UserEntity;
import com.avocado.tatevik.retail.security.jwt.model.JwtUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class JwtUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public JwtUserDetail loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username.toLowerCase()).orElseThrow(
                () -> new UsernameNotFoundException("User with name = " + username + " was not found"));
        return convert(user);
    }

    public JwtUserDetail loadUserByUsernameAndPassword(String username, String password) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username.toLowerCase()).orElseThrow(
                () -> new UsernameNotFoundException("User with name = " + username + " was not found"));
        String passwordHash = user.getPasswordHash();
        if (passwordEncoder.matches(password, passwordHash)) {
            JwtUserDetail userDetail = convert(user);
            userDetail.setAuthorities(getAuthorities(user));
            return userDetail;
        } else throw new BadCredentialsException("Invalid password for username " + username);
    }

    private Collection<? extends GrantedAuthority> getAuthorities(UserEntity user) {
        return user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                .collect(Collectors.toList());
    }

    private JwtUserDetail convert(UserEntity user) {
        JwtUserDetail userDetail = new JwtUserDetail();
        userDetail.setAuthorities(user.getRoles());
        userDetail.setPasswordHash(user.getPasswordHash());
        userDetail.getJwtUserDto().setId(user.getId());
        userDetail.getJwtUserDto().setUsername(user.getUsername());
        return userDetail;
    }
}

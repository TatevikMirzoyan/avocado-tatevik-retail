package com.avocado.tatevik.retail.security.service;

import com.avocado.tatevik.retail.security.jwt.model.JwtUserDetail;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {

    @Value("${security.jwt.token.secret}")
    private String SECRET;

    @Value(value = "${security.jwt.expiration.seconds}")
    public Long JWT_TOKEN_EXPIRATION_SECONDS;

    private final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);

    public String generateToken(JwtUserDetail userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userDetailId", userDetails.getJwtUserDto().getId());
        claims.put("username", userDetails.getUsername());
        claims.put("roles", userDetails.getAuthorities());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_EXPIRATION_SECONDS))
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();
    }

    public String getUsername(String token) {
        return getAllClaimsFromToken(token).getSubject().split(",")[1];
    }

    public Date getExpirationDate(String token) {
        Claims claims = getAllClaimsFromToken(token);
        return claims.getExpiration();
    }

    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDate(token);
        return expiration.before(new Date());
    }

    public void validate(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature - {}", ex.getMessage());
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token - {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token - {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token - {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty - {}", ex.getMessage());
        }
    }

    //for retrieving any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        validate(token);
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
}

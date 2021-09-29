package com.avocado.tatevik.retail.security.filter;

import com.avocado.tatevik.retail.common.exception.auth.UnauthorizedException;
import com.avocado.tatevik.retail.security.auth.AuthRequest;
import com.avocado.tatevik.retail.security.auth.AuthResponse;
import com.avocado.tatevik.retail.security.auth.dto.AuthErrorDto;
import com.avocado.tatevik.retail.security.facade.AuthenticationFacade;
import com.avocado.tatevik.retail.security.jwt.model.JwtUserDetail;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.persistence.EntityNotFoundException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    private final AuthenticationFacade authenticationFacade;

    @Autowired
    public JWTAuthenticationFilter(AuthenticationFacade authenticationFacade) {
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        final AuthRequest credentials;
        try {
            credentials = new ObjectMapper().readValue(request.getInputStream(), AuthRequest.class);
            if (credentials.getUsername() == null || StringUtils.isEmpty(credentials.getPassword())) {
                throw new IllegalArgumentException("Username or password is missing.");
            }
            final AuthResponse authenticationResponse = authenticationFacade.authenticateByCredentials(credentials);
            final JwtUserDetail userDetail = authenticationResponse.getUserDetail();
            return new UsernamePasswordAuthenticationToken(userDetail, authenticationResponse.getJwtToken(), userDetail.getAuthorities());
        } catch (EntityNotFoundException | UnauthorizedException | AuthenticationException e) {
            logger.error(e.getMessage());
            handleErrorResponse(response, HttpServletResponse.SC_FORBIDDEN, e.getMessage());
            return null;
        } catch (Exception e) {
            logger.error(e.getMessage());
            handleErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST, String.format("Something went wrong during Authentication credentials reading:'%s'", e.getMessage()));
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException {
        String token = (String) auth.getCredentials();
        try {
            res.setHeader("Access-Control-Allow-Origin", "*");
            res.getWriter().append(token);
            res.addHeader("Authorization", "Bearer " + token);
        } catch (IOException e) {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            logger.error(e.getMessage());
        }
    }

    private void handleErrorResponse(final HttpServletResponse res, final int status, final String message) {
        try {
            logger.info("AUTHORIZATION FAILURE Reason:'{}'", message);
            SecurityContextHolder.getContext().setAuthentication(null);
            res.setStatus(status);
            final AuthErrorDto authErrorDTO = new AuthErrorDto();
            authErrorDTO.setStatus(status);
            authErrorDTO.setMessage(message);
            res.getWriter().write(authErrorDTO.toString());
//            res.flushBuffer();
//            res.sendError(status, message);
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.info("AUTHORIZATION FAILURE Reason:'{}'", e.getMessage());
            SecurityContextHolder.getContext().setAuthentication(null);
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}

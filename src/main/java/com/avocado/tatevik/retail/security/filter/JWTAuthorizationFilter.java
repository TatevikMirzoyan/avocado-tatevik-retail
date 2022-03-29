package com.avocado.tatevik.retail.security.filter;

import com.avocado.tatevik.retail.common.exception.auth.AuthenticationTokenExpiredException;
import com.avocado.tatevik.retail.common.exception.auth.AuthenticationTokenMissingException;
import com.avocado.tatevik.retail.common.exception.auth.UnauthorizedException;
import com.avocado.tatevik.retail.security.auth.AuthResponse;
import com.avocado.tatevik.retail.security.auth.dto.AuthErrorDto;
import com.avocado.tatevik.retail.security.facade.AuthenticationFacade;
import com.avocado.tatevik.retail.security.jwt.model.JwtUserDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private final Logger logger = LoggerFactory.getLogger(JWTAuthorizationFilter.class);

    private final AuthenticationFacade authenticationFacade;

    public JWTAuthorizationFilter(AuthenticationFacade authenticationFacade) {
        super(authenticationFacade);
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {

        final String headerTatevik = req.getHeader("tatevik");
        if (headerTatevik != null && headerTatevik.contains("awesome girl")) {
            logger.info("There is a header 'tatevik' which value is 'awesome girl'");
        }

        final String header = req.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            logger.error("Request doesn't have Authorization header.");
            chain.doFilter(req, res);
        } else {
            try {
                final AuthResponse authentication = getAuthentication(header);
                final JwtUserDetail userDetail = authentication.getUserDetail();
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetail, authentication.getJwtToken(), userDetail.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));

                SecurityContextHolder.getContext().setAuthentication(authToken);
                logger.info("SUCCESSFUL AUTHORIZATION for User:'{}'.", userDetail.getJwtUserDto() == null ? "UNKNOWN" : userDetail.getJwtUserDto().getUsername());
                chain.doFilter(req, res);
            } catch (AuthenticationTokenExpiredException e) {
                logger.error(e.getMessage());
                handleErrorResponse(res, HttpServletResponse.SC_UNAUTHORIZED, "Authentication token is expired.");
            } catch (AuthenticationTokenMissingException | UnauthorizedException e) {
                logger.error(e.getMessage());
                handleErrorResponse(res, HttpServletResponse.SC_FORBIDDEN, "Authentication token was not found in database.");
            } catch (AuthenticationException e) {
                logger.error(e.getMessage());
                handleErrorResponse(res, HttpServletResponse.SC_UNAUTHORIZED, "Authentication was not successful.");
            } catch (Exception e) {
                logger.error(e.getMessage());
                handleErrorResponse(res, HttpServletResponse.SC_BAD_REQUEST, String.format("Something went wrong during Authentication credentials reading:'%s'", e.getMessage()));
                res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }

    private AuthResponse getAuthentication(final String header) throws AuthenticationTokenExpiredException, AuthenticationTokenMissingException {
        final String token = header.replaceAll("Bearer ", "");
        return authenticationFacade.authenticateApiAccessToken(token);
    }

    private void handleErrorResponse(final HttpServletResponse res, final int status, final String message) {
        try {
            logger.info("AUTHORIZATION FAILURE Reason:'{}'", message);
            SecurityContextHolder.getContext().setAuthentication(null);
            res.setStatus(status);
            final AuthErrorDto authErrorDto = new AuthErrorDto();
            authErrorDto.setStatus(status);
            authErrorDto.setMessage(message);
            res.getWriter().write(authErrorDto.toString());
            res.flushBuffer();
        } catch (Exception e) {
            logger.info("AUTHORIZATION FAILURE Reason:'{}'", e.getMessage());
            SecurityContextHolder.getContext().setAuthentication(null);
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}

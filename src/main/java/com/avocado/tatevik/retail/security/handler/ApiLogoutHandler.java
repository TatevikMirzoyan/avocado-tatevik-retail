package com.avocado.tatevik.retail.security.handler;

import com.avocado.tatevik.retail.security.facade.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ApiLogoutHandler implements LogoutHandler {

    private final AuthenticationFacade authenticationFacade;

    @Autowired
    public ApiLogoutHandler(final AuthenticationFacade authenticationFacade) {
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        final String header = request.getHeader("Authorization");
        final boolean fromAllDevices = Boolean.parseBoolean(request.getParameter("all"));
        if (header != null && header.startsWith("Bearer ")) {
            final String token = header.replaceAll("Bearer ", "");
            if (fromAllDevices) {
                authenticationFacade.logoutFromAllDevices(token);
            } else {
                authenticationFacade.logout(token);
            }
//            SecurityContextHolder.getContext().setAuthentication(null);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);    
        }
    }
}

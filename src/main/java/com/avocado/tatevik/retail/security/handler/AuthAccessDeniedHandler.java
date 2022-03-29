package com.avocado.tatevik.retail.security.handler;

import com.avocado.tatevik.retail.common.exception.enums.ExceptionCode;
import com.avocado.tatevik.retail.common.exception.response.ErrorResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthAccessDeniedHandler implements AccessDeniedHandler {

    private final Logger logger = LoggerFactory.getLogger(AuthAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest req, HttpServletResponse res, AccessDeniedException e) throws IOException {
        logger.error(e.getLocalizedMessage());
        ServletServerHttpResponse response = new ServletServerHttpResponse(res);
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getServletResponse().setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        response.getBody().write(new ErrorResponseDto("Access is denied", ExceptionCode.UUTI_45).toString().getBytes());
    }
}
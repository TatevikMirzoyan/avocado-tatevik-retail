package com.avocado.tatevik.retail.security.filter.logfilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

@Component
public class LogInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(LogInterceptor.class);

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        writeResponse(response);
    }

    public String getHeaders(HttpServletRequest request) {
        StringBuilder rawHeaders = new StringBuilder("\n");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            rawHeaders.append(key).append(":").append(value).append("\n");
        }
        return rawHeaders.toString();
    }

    public String getHeaders(HttpServletResponse response) {
        StringBuilder rawHeaders = new StringBuilder("\n");
        Set<String> headerNames = new HashSet<>(response.getHeaderNames());
        for (String key : headerNames) {
            String value = response.getHeader(key);
            rawHeaders.append(key).append(":").append(value).append("\n");
        }
        return rawHeaders.toString();
    }

    public void writeRequest(HttpServletRequest request) {
        String requestHeaders = getHeaders(request);
        logger.info("REQUEST METHOD:'{}'", request.getMethod());
        logger.info("REQUEST HEADERS:'{}'", requestHeaders);
    }

    public void writeResponse(HttpServletResponse response) {
        String responseHeaders = getHeaders(response);
        logger.info("RESPONSE STATUS:'{}'", response.getStatus());
        logger.info("RESPONSE HEADERS:'{}'", responseHeaders);
    }
}

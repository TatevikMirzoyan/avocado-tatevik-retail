package com.avocado.tatevik.retail.security.filter.logfilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class LogFilter extends HttpFilter {

    @Autowired
    private LogInterceptor logInterceptor;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logInterceptor.writeRequest((HttpServletRequest) request);
        chain.doFilter(request, response);
    }
}
package com.userManagement.security.handler;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private String failureUrl;

    @Resource
    private RedirectStrategy redirectStrategy;
    protected static final String STATUS_MESSAGE_AUTHENTICATION_FAILED = "Bad credentials";


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, STATUS_MESSAGE_AUTHENTICATION_FAILED);
        redirectStrategy.sendRedirect(request, response, getFailureUrl());
    }

    public String getFailureUrl() {
        return failureUrl;
    }

    public void setFailureUrl(String failureUrl) {
        this.failureUrl = failureUrl;
    }
}
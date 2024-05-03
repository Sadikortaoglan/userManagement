package com.usermanagement.controllers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
@Scope("session")
public class LoginController {
    protected static final String VIEW_NAME_LOGIN_PAGE = "login";

    @GetMapping()
    public String showLoginPage() {
        return VIEW_NAME_LOGIN_PAGE;
    }
}

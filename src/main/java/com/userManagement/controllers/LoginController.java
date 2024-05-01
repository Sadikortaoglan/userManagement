package com.userManagement.controllers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/login")
@Scope("session")
public class LoginController {
    protected static final String VIEW_NAME_LOGIN_PAGE = "login";

    @GetMapping()
    public String showLoginPage() {
        //LOGGER.debug("Rendering login page.");
        return VIEW_NAME_LOGIN_PAGE;
    }
}

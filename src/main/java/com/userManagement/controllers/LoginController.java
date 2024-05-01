package com.userManagement.controllers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
@Scope("session")
public class LoginController {
    @GetMapping
    public String getLoginPage(Model model) {
        model.addAttribute("title", "Giris Yap");
        return "login";
    }
}

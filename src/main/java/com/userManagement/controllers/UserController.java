package com.userManagement.controllers;

import com.userManagement.data.UserData;
import com.userManagement.services.UserService;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@Scope("session")
public class UserController {
    private static final String REDIRECT_PREFIX = "redirect:/user";
    private static final String REDIRECT_SAVE = "/save";
    @Resource
    UserService userService;

    @PostMapping(value = "/save")
    public String saveUser(@ModelAttribute UserData userData, BindingResult result, Model model) {
        boolean hasErrors = result.hasErrors();
        boolean savedSuccessfully = !hasErrors && userService.registerUser(userData);

        if (savedSuccessfully) {
            model.addAttribute("successMessage", "Kayıt başarıyla oluşturuldu.");
        }
        model.addAttribute("error", hasErrors || !savedSuccessfully);

        return getRedirectUrl(hasErrors || !savedSuccessfully, userData.getId());
    }

    private String getRedirectUrl(boolean hasError, String userId) {
        String redirectUrl = REDIRECT_PREFIX;
        if (hasError) {
            redirectUrl += REDIRECT_SAVE;
            if (StringUtils.hasText(userId)) {
                redirectUrl += "?id=" + userId;
            }
        }
        return redirectUrl;
    }
}

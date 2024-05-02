package com.userManagement.controllers;

import com.userManagement.data.UserData;
import com.userManagement.services.UserService;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
@Scope("session")
public class UserController {
    private static final String REDIRECT_PREFIX = "redirect:/user";
    private static final String REDIRECT_SAVE = "/save";
    @Resource
    UserService userService;
    @GetMapping
    public String getUsersPage(Model model) {
        model.addAttribute("title", "title");
        model.addAttribute("userList", userService.getAllActiveUsers());
        return "userList";
    }
    @RequestMapping(value = { "/registerUser" }, method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        UserData user = new UserData();
        model.addAttribute("user", user);
        return "registerUser";
    }
    @PostMapping(value = "/registerUser")
    public String saveUser(@ModelAttribute UserData userData, BindingResult result, Model model) {
        boolean hasErrors = result.hasErrors();
        boolean savedSuccessfully = !hasErrors && userService.save(userData);

        if (savedSuccessfully) {
            model.addAttribute("successMessage", "Kayıt başarıyla oluşturuldu.");
        }
        model.addAttribute("error", hasErrors || !savedSuccessfully);

        return getRedirectUrl(hasErrors || !savedSuccessfully, userData.getId());
    }
    @GetMapping("users")
    public String  getAllActiveUsers(Model model){
        model.addAttribute("getAllUser",userService.getAllActiveUsers());
        return "users";
    }
    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam long userId, Model model) {
        boolean deleted = userService.deleteUser(userId);
        model.addAttribute("deleted", deleted);
        return "deleteUserResult";
    }
    @PostMapping("/users/{id}/update")
    public String updateUser(@ModelAttribute("user") UserData userData) {
        boolean updated = userService.save(userData);
        if (updated) {
            return "redirect:/users/" + userData.getId();
        } else {
            //TODO entegrasyonu yaptıktan sonra log yazmayı unutma
            return "errorPage";
        }
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

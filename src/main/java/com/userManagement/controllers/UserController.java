package com.userManagement.controllers;

import com.userManagement.data.ResultData;
import com.userManagement.data.UserData;
import com.userManagement.services.UserService;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private static final String REDIRECT_PREFIX = "redirect:/user";
    private static final String REDIRECT_SAVE = "/registerUser";
    @Resource
    UserService userService;
    @GetMapping
    public String getUsersPage(Model model) {
        model.addAttribute("title", "title");
        model.addAttribute("userList", userService.getAllActiveUsers());
        return "userList";
    }
    @RequestMapping(value = "/registerUser")
    @GetMapping
    public String newUser(ModelMap model) {
        UserData user = new UserData();
        model.addAttribute("user", user);
        return "registerUser";
    }
    @PostMapping(value = "/registerUser",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public String saveUser(@ModelAttribute UserData userData, BindingResult result, Model model) {
        boolean hasErrors = result.hasErrors();
        boolean savedSuccessfully = !hasErrors && userService.saveUser(userData);

        if (savedSuccessfully) {
            model.addAttribute("successMessage", "Kayıt başarıyla oluşturuldu.");
        } else {
            model.addAttribute("error", true);
        }

        return getRedirectUrl(hasErrors || !savedSuccessfully, userData.getId());
    }
    @PostMapping(value = "/deleteUser/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResultData deleteUser(@PathVariable String id) {
        ResultData result = new ResultData();

        boolean deleted = userService.deleteUser(id);

        if (deleted) {
            result.setMessage("Kullanıcı başarıyla silindi.");
        } else {
            result.setMessage("Kullanıcı silinirken bir hata oluştu.");
        }

        return result;
    }
    @GetMapping("/getUserById/{id}")
    public ResponseEntity<UserData> getUserById(@PathVariable String id) {
        UserData user = userService.findById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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

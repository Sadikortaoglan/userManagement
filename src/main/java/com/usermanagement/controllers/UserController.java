package com.usermanagement.controllers;

import com.usermanagement.data.UserData;
import com.usermanagement.models.UserModel;
import com.usermanagement.services.UserService;
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
    @GetMapping(value = "/registerUser")
    public String newUser(ModelMap model) {
        UserData user = new UserData();
        model.addAttribute("user", user);
        return "registerUser";
    }
    @PostMapping(value = "/registerUser")
    public String saveUser(@ModelAttribute UserData userData, BindingResult result, Model model) {
        boolean hasErrors = result.hasErrors();
        boolean savedSuccessfully = !hasErrors && userService.saveUser(userData);

        if (savedSuccessfully) {
            model.addAttribute("success", true);
        } else {
            model.addAttribute("error", true);
        }

        return getRedirectUrl(hasErrors || !savedSuccessfully, userData.getId());
    }
    @PutMapping("/updateUser")
    public ResponseEntity<Boolean> updateUser(@RequestBody UserData userData) {
        boolean success = userService.saveUser(userData);
        return ResponseEntity.ok(success);
    }
    @PostMapping(value = "/deleteUser/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean deleteUser(@PathVariable String id) {
        return userService.deleteUser(id);
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
    @GetMapping("/checkUserNameAvailability")
    @ResponseBody
    public boolean checkUserNameAvailability(@RequestParam String userName) {
        UserModel userModel = userService.getByUserName(userName);
        return userModel != null;
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

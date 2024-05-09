package com.usermanagement.controllers;

import com.usermanagement.data.UserData;
import com.usermanagement.models.UserModel;
import com.usermanagement.services.UserService;
import jakarta.annotation.Resource;
import jakarta.validation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping("/user")
@Scope("session")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private static final String REDIRECT_PREFIX = "redirect:/user";
    private static final String REDIRECT_SAVE = "/registerUser";
    private static final String REDIRECT_ERROR =REDIRECT_PREFIX+ "/registerUser?error=true";
    @Resource
    UserService userService;
    @GetMapping
    public String getUsersPage(Model model) {
        model.addAttribute("title", "title");
        model.addAttribute("userList", userService.getAllActiveUsers());
        return "userList";
    }
    @GetMapping(value = "/registerUser")
    public String newUser(ModelMap model, @RequestParam(name = "error", required = false) Boolean error) {
        UserData user = new UserData();
        model.addAttribute("user", user);

        if (error != null && error) {
            model.addAttribute("error", true);
        }
        return REDIRECT_SAVE;
    }
    @PostMapping(value = "/registerUser")
    public String saveUser(@Valid @ModelAttribute UserData userData,BindingResult result, Model model) {
        Set<ConstraintViolation<UserData>> violations = getValidator().validate(userData);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<UserData> violation : violations) {
                LOGGER.error(violation.getMessage());
            }
            model.addAttribute("error", true);
            return REDIRECT_ERROR;
        }
        boolean savedSuccessfully = userService.saveUser(userData);
        if (!savedSuccessfully) {
            model.addAttribute("error", true);
            return REDIRECT_SAVE;
        }

        return REDIRECT_PREFIX;
    }

    private Validator getValidator(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return factory.getValidator();
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
}

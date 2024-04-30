package com.userManagement.services;

import com.userManagement.data.UserData;
import org.springframework.ui.Model;

public interface UserService {
    boolean registerUser(UserData user);
}

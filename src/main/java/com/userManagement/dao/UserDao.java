package com.userManagement.dao;

import com.userManagement.models.UserModel;

public interface UserDao {
    boolean registerUser(UserModel userModel);
    UserModel findById(String email);
}

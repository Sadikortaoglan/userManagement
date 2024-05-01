package com.userManagement.dao;

import com.userManagement.models.UserModel;

import java.util.List;

public interface UserDao {
    boolean save(UserModel userModel);

    UserModel findById(Long userId);

    List<UserModel> getAllActiveUsers();
}

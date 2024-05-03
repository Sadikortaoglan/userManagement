package com.usermanagement.dao;

import com.usermanagement.models.UserModel;

import java.util.List;

public interface UserDao {
    boolean save(UserModel userModel);

    UserModel findById(String userId);

    List<UserModel> getAllActiveUsers();
    UserModel getByUserName(String username);

}

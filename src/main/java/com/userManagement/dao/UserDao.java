package com.userManagement.dao;

import com.userManagement.models.UserModel;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserDao {
    boolean save(UserModel userModel);

    UserModel findById(String userId);

    List<UserModel> getAllActiveUsers();
    UserDetails getByUserName(String username);

}

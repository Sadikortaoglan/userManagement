package com.usermanagement.services;

import com.usermanagement.data.UserData;
import com.usermanagement.models.UserModel;

import java.util.List;

public interface UserService {
    boolean saveUser(UserData user);
    boolean deleteUser(String userId);
    UserData findById(String id);
    List<UserData> getAllActiveUsers();
    UserModel getByUserName(String parameter);
}

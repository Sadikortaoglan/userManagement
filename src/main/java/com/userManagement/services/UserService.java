package com.userManagement.services;

import com.userManagement.data.UserData;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    boolean saveUser(UserData user);
    boolean deleteUser(String userId);
    UserData findById(String id);
    List<UserData> getAllActiveUsers();
    UserDetails getByUserName(String parameter);
}

package com.userManagement.services;

import com.userManagement.data.UserData;
import com.userManagement.models.UserModel;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public interface UserService {
    boolean registerUser(UserData user);
    boolean deleteUser(long userId);
    UserModel findById(Long id);
    List<UserModel> getAllActiveUsers();


}

package com.userManagement.services;

import com.userManagement.data.UserData;
import com.userManagement.models.UserModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    boolean save(UserData user);
    boolean deleteUser(long userId);
    UserModel findById(Long id);
    List<UserModel> getAllActiveUsers();


}

package com.userManagement.services.impl;

import com.userManagement.dao.UserDao;
import com.userManagement.data.UserData;
import com.userManagement.models.UserModel;
import com.userManagement.services.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    public boolean registerUser(UserData userDto) {
        UserModel existingUser = userDao.findById(userDto.getEmail());
        if (existingUser != null) {
            return false;
        } else {
            UserModel user = convertToUser(userDto);
            return userDao.registerUser(user);
        }
    }
    private UserModel convertToUser(UserData userDto) {
        UserModel user = new UserModel();
        user.setUserName(userDto.getUserName());
        user.setPasswd(userDto.getPasswd());
        user.setEmail(userDto.getEmail());
        return user;
    }
    private UserData convertToUserData(UserModel user) {
        UserData userData = new UserData();
        userData.setId(user.getId());
        userData.setUserName(user.getUserName());
        userData.setPasswd(user.getPasswd());
        userData.setEmail(user.getEmail());
        return userData;
    }
}

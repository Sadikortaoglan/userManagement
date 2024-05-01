package com.userManagement.services.impl;

import com.userManagement.dao.UserDao;
import com.userManagement.data.UserData;
import com.userManagement.models.UserModel;
import com.userManagement.services.UserService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    public boolean registerUser(UserData userDto) {
        UserModel existingUser = userDao.findById(Long.parseLong(userDto.getId()));
        if (existingUser != null) {
            return false;
        } else {
            UserModel user = convertToUser(userDto);
            return userDao.registerUser(user);
        }
    }

    @Override
    public boolean deleteUser(long userId) {
        UserModel existingUser = userDao.findById(userId);
        if (existingUser != null) {
            existingUser.setDeleted(false);
            return userDao.registerUser(existingUser);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with ID: " + userId);
        }
    }
    @Override
    public List<UserModel> getAllActiveUsers() {
        return userDao.getAllActiveUsers();
    }


    @Override
    public UserModel findById(Long id) {
        return userDao.findById(id);
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
        userData.setUserName(user.getUserName());
        userData.setPasswd(user.getPasswd());
        userData.setEmail(user.getEmail());
        return userData;
    }
}

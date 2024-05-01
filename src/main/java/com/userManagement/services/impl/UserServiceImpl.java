package com.userManagement.services.impl;

import com.userManagement.dao.UserDao;
import com.userManagement.data.UserData;
import com.userManagement.models.UserModel;
import com.userManagement.services.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    public boolean save(UserData userDto) {
        UserModel existingUser = userDao.findById(Long.parseLong(userDto.getId()));
        if (existingUser != null) {
            return false;
        } else {
            //UserModel user = convertUsersToModels(userDto);
            return false;
        }
    }

    @Override
    public boolean deleteUser(long userId) {
        UserModel existingUser = userDao.findById(userId);
        if (existingUser != null && existingUser.isDeleted()) {
            return false;
        } else if (existingUser != null) {
            existingUser.setDeleted(true);
            return userDao.save(existingUser);
        } else {
            return false;
        }
    }
    @Override
    public List<UserData> getAllActiveUsers() {
        List<UserModel> users = userDao.getAllActiveUsers();
        return convertUsersToModels(users);
    }

    @Override
    public UserDetails getByUserName(String parameter) {
        return null;
    }


    @Override
    public UserModel findById(Long id) {
        return userDao.findById(id);
    }

    public List<UserData> convertUsersToModels(List<UserModel> userModelList) {
        List<UserData> userDataList = new ArrayList<>();
        for (UserModel user : userModelList) {
            UserData userData = convertToUserData(user);
            userDataList.add(userData);
        }
        return userDataList;
    }

    private UserData convertToUserData(UserModel userModel) {
        UserData userData = new UserData();
        userData.setUserName(userModel.getUserName());
        userData.setPasswd(userModel.getPasswd());
        userData.setEmail(userModel.getEmail());
        return userData;
    }
}

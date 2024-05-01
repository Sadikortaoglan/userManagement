package com.userManagement.services.impl;

import com.userManagement.dao.UserDao;
import com.userManagement.data.UserData;
import com.userManagement.models.UserModel;
import com.userManagement.services.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService {
    @Resource
    private UserDao userDao;

    public boolean save(UserData userDto) {
        UserModel existingUser = userDao.findById(Long.parseLong(userDto.getId()));
        if (existingUser != null) {
            return false;
        } else {
            UserModel user = convertToUser(userDto);
            return userDao.save(user);
        }
    }

/*
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
    public List<UserModel> getAllActiveUsers() {
        return userDao.getAllActiveUsers();
    }

    @Override
    public UserDetails getByUserName(String parameter) {
        return null;
    }


    @Override
    public UserModel findById(Long id) {
        return userDao.findById(id);
    }
*/

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}

package com.userManagement.services.impl;

import com.userManagement.dao.UserDao;
import com.userManagement.data.UserData;
import com.userManagement.models.UserModel;
import com.userManagement.services.UserService;
import jakarta.annotation.Resource;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserDao userDao;
    @Resource
    private ModelMapper modelMapper;

    @Override
    public boolean saveUser(UserData userData) {
        UserModel userModel = getUserModel(userData);
        if(userModel == null){
            LOGGER.error("saveUserExp: User cannot be null.");
            return false;
        }
        return userDao.save(userModel);
    }
    private UserModel getUserModel(UserData userData) {
        UserModel userModel;
        if (StringUtils.hasText(userData.getId())) {
            userModel = userDao.findById(userData.getId());
            modelMapper.map(userData, userModel);
        } else {
            userModel = modelMapper.map(userData, UserModel.class);
        }
        return userModel;
    }
    @Override
    public boolean deleteUser(String userId) {
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
        return users.stream()
                .map(userModel -> modelMapper.map(userModel, UserData.class))
                .collect(Collectors.toList());
    }
    @Override
    public UserDetails getByUserName(String username) {
        return userDao.getByUserName(username);
    }
    @Override
    public UserData findById(String id) {
        UserModel user= userDao.findById(id);
        UserData userData=modelMapper.map(user, UserData.class);
        return userData;
    }
}

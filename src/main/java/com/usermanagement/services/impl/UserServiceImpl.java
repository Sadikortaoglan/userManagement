package com.usermanagement.services.impl;

import com.usermanagement.dao.UserDao;
import com.usermanagement.data.UserData;
import com.usermanagement.models.UserModel;
import com.usermanagement.models.enums.UserRole;
import com.usermanagement.services.UserService;
import jakarta.annotation.Resource;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

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
        userModel.setRole(userData.isAdmin() ? UserRole.ADMIN_ROLE : UserRole.USER_ROLE);
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
                .map(userModel -> modelMapper.map(userModel, UserData.class)).toList();
    }
    @Override
    public UserModel getByUserName(String username) {
        return userDao.getByUserName(username);

    }
    @Override
    public UserData findById(String id) {
        UserModel user= userDao.findById(id);
        UserData userData=modelMapper.map(user, UserData.class);
        userData.setAdmin(user.getRole().equals(UserRole.ADMIN_ROLE));
        return userData;
    }
}

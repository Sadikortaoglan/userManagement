package com.userManagement.dao.impl;

import com.userManagement.dao.UserDao;
import com.userManagement.models.UserModel;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public class UserDaoImpl implements UserDao {
    @Resource
    private SessionFactory sessionFactory;

    @Override
    public boolean registerUser(UserModel userModel) {
        try {
            Session session= sessionFactory.getCurrentSession();
            session.saveOrUpdate(userModel);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public UserModel findById(String email) {
        return null;
    }
}

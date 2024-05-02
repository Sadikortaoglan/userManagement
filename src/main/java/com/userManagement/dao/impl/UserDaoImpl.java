package com.userManagement.dao.impl;

import com.userManagement.dao.UserDao;
import com.userManagement.models.UserModel;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;


@Transactional
@Repository
public class UserDaoImpl implements UserDao {
    @Resource
    private SessionFactory sessionFactory;

    @Override
    public boolean save(UserModel userModel) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(userModel);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public UserModel findById(Long id) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query<UserModel> query = session.createQuery("FROM UserModel WHERE id = :userId", UserModel.class);
            query.setParameter("userId", id);
            return query.uniqueResult();
        } catch (HibernateException e) {
            //TODO Loglama yap burada
            e.printStackTrace();
            return null;
        }
    }
    public List<UserModel> getAllActiveUsers() {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query<UserModel> query = session.createQuery("FROM UserModel WHERE isDeleted = :userDelete", UserModel.class);
            query.setParameter("userDelete", false);
            List<UserModel> userList = query.getResultList();
            return userList;
        } catch (Exception e) {
            //LOGGER.error("getUsersExp: ", e);
            return Collections.emptyList();
        }
    }
    @Override
    public UserDetails getByUserName(String username) {
        try{
            Session session = sessionFactory.getCurrentSession();
            Query<UserDetails> query = session.createQuery("FROM UserModel WHERE userName = :username", UserDetails.class);
            query.setParameter("username", username);
            return query.uniqueResult();
        } catch (Exception e) {
            // Log the exception or handle it according to your application's requirements
            return null;
        }
    }

}

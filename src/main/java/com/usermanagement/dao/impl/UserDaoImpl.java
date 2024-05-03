package com.usermanagement.dao.impl;

import com.usermanagement.dao.UserDao;
import com.usermanagement.models.UserModel;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Transactional

@Repository
public class UserDaoImpl implements UserDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public boolean save(UserModel userModel) {
        try {
            Session session = sessionFactory.getCurrentSession();
            if(userModel.getId() != null){
                session.merge(userModel);
            } else{
                session.persist(userModel);
            }
            return true;
        } catch (Exception e) {
            LOGGER.error("save",e);
            return false;
        }
    }

    @Override
    public UserModel findById(String id) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query<UserModel> query = session.createQuery("FROM UserModel WHERE id = :userId", UserModel.class);
            query.setParameter("userId", id);
            return query.uniqueResult();
        } catch (HibernateException e) {
            LOGGER.error("findById",e);
            return null;
        }
    }
    public List<UserModel> getAllActiveUsers() {
        try {
            Session session = sessionFactory.getCurrentSession();
            return session.createQuery("FROM UserModel WHERE isDeleted = :userDelete", UserModel.class)
            .setParameter("userDelete", false)
                    .getResultList();
        } catch (Exception e) {
            LOGGER.error("getAllActiveUsers",e);
            return Collections.emptyList();
        }
    }
    @Override
    public UserModel getByUserName(String username) {
        try{
            Session session = sessionFactory.getCurrentSession();
            Query<UserModel> query = session.createQuery("FROM UserModel WHERE userName = :username", UserModel.class);
            query.setParameter("username", username);
            return query.uniqueResult();
        } catch (Exception e) {
            LOGGER.error("getByUserName",e);
            return null;
        }
    }

}

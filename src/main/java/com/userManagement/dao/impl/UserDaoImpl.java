package com.userManagement.dao.impl;

import com.userManagement.dao.UserDao;
import com.userManagement.models.UserModel;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

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

    @Override
    public List<UserModel> getAllActiveUsers() {
        try{
            Session session = sessionFactory.getCurrentSession();
            return session.createQuery("FROM UserModel WHERE isDeleted = true", UserModel.class)
                    .getResultList();
        }catch (HibernateException e){
            e.printStackTrace();
            return null;
        }

    }
}

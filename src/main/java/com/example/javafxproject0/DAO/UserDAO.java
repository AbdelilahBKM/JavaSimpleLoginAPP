package com.example.javafxproject0.DAO;

import com.example.javafxproject0.business.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.util.List;


public class UserDAO implements IUser{
    private final EntityManager entityManager;
    public UserDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User saveUser(User user) {
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(user);
            entityManager.getTransaction().commit();
            return user;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public User updateUser(User user) {
        entityManager.getTransaction().begin();
        try {
            entityManager.merge(user);
            entityManager.getTransaction().commit();
            return user;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public void deleteUser(long id) {
        entityManager.getTransaction().begin();
        try{
            User user = entityManager.find(User.class, id);
            entityManager.remove(user);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public User getUser(long id) {
        try {
        User user = entityManager.find(User.class, id);
        return user;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public User getUser(String email) {
        try{

        User user = entityManager.createQuery("from User where email = :email", User.class)
                .setParameter("email", email)
                .getSingleResult();
        return user;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<User> getUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }
}

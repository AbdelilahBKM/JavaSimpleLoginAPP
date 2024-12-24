package com.example.javafxproject0.DAO;

import com.example.javafxproject0.business.User;

import java.util.List;

public interface IUser {
    public User saveUser(User user);
    public User updateUser(User user);
    public void deleteUser(long id);
    public User getUser(long id);
    public User getUser(String email);
    public List<User> getUsers();
}

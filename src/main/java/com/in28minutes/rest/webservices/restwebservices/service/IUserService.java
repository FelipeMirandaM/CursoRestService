package com.in28minutes.rest.webservices.restwebservices.service;

import com.in28minutes.rest.webservices.restwebservices.model.User;

import java.util.List;

public interface IUserService {

    public List<User> findAll();

    public User findById(Integer id);

    public User save(User user);

}

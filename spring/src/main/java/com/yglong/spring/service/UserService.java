package com.yglong.spring.service;

import com.yglong.spring.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAllUsers();
    User save(User user);
    User update(User user);
    void delete(User user);
    User getByName(String name);
}

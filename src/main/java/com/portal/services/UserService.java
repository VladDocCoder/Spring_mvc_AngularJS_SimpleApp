package com.portal.services;


import com.portal.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    User findById(Long id);
    User findByName(String name);
    void saveUser(User user);
    ResponseEntity<User> updateUser(User realUser, User user);
    void deleteUserById(long id);
    public List<User> getAllUsers();
    boolean isUserExist(User user);
}

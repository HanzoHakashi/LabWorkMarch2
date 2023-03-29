package com.example.LabWorkMarch_2.service;

import com.example.LabWorkMarch_2.dao.UserDao;
import com.example.LabWorkMarch_2.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void registration(String username,String email,String password){
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        userDao.save(user);
    }
}

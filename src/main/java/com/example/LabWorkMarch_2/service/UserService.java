package com.example.LabWorkMarch_2.service;

import com.example.LabWorkMarch_2.dao.UserDao;
import com.example.LabWorkMarch_2.dto.UserDto;
import com.example.LabWorkMarch_2.entity.User;

import com.example.LabWorkMarch_2.exeption.ResourceNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserDao userDao;


    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public Long findUserByEmail(String email) {
        Optional<User> userOptional = userDao.findUserByEmail(email);
        var user = userOptional
                .orElseThrow(()-> new ResourceNotFoundException("Не было найдено такого пользователя"));
        if (user == null) {
            throw new RuntimeException("User not found for email: " + email);
        }
        return user.getId();
    }

    public void registration(String username,String email,String password){
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        userDao.save(user);
    }
}

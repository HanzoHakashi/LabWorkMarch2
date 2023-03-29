package com.example.LabWorkMarch_2.util;

import com.example.LabWorkMarch_2.dao.TaskDao;
import com.example.LabWorkMarch_2.dao.UserDao;
import com.example.LabWorkMarch_2.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Configuration
public class InitDatabase {
        @Bean
    CommandLineRunner init(UserDao userDao, TaskDao taskDao){
            return args -> {
                userDao.createTable();
                taskDao.createTable();

                taskDao.deleteAll();
                userDao.deleteAll();

                User user1 = new User();
                user1.setUsername("SuperSaiyan");
                user1.setEmail("ssj@gmail.com");
                user1.setPassword("kamehameha213");
                userDao.save(user1);
            };
        }
}

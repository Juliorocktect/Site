package com.stream.Site.Service;

import com.stream.Site.Model.User;
import com.stream.Site.Repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    void newUser() {
        UserService userService = new UserService();
        User newUser = new User("1234", "123", "3331dfrg", "sdf", "sdfgsd", "sdfsdf");
        assertEquals(true, userService.newUser(newUser));
    }

    @Test
    void getCurrentUser() {
    }

    @Test
    void removePasswordFromCurrentUser() {
    }

    @Test
    void getAllUsers() {
    }

    @Test
    void getUserPerId() {
    }

    @Test
    void checkIfUserExists() {
    }

    @Test
    void userLogin() {
    }

    @Test
    void checkIfPassWordIsTrue() {
    }

    @Test
    void checkIfUserNameIsAvailable() {
    }
}
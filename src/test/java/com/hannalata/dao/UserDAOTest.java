package com.hannalata.dao;

import com.hannalata.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {

    List<User> users;
    @BeforeEach
    void setUp() {
        users = new ArrayList<>();
        User user = new User("myhailovna", "monolit7",
                "Hanna", "Lata", "test@gmail.com", "0975554433");
        users.add(user);
    }

    @AfterEach
    void tearDown() {
        users.forEach(it -> UserDAO.delete(it));
    }

    @Test
    void saveAndGetAndDelete() {
        User savedUser = UserDAO.save(users.get(0));
        assertNotNull(savedUser);
        assertNotNull(savedUser.getId());

        User retrievedUser = UserDAO.findOne(savedUser.getId());
        assertNotNull(retrievedUser);
        assertEquals("myhailovna", retrievedUser.getLogin());

        UserDAO.delete(retrievedUser);
        User deletedUser = UserDAO.findOne(savedUser.getId());
        assertNull(deletedUser);

    }
}
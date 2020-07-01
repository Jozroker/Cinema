package com.cinema.point.repository;

import com.cinema.point.domain.Role;
import com.cinema.point.domain.User;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserRepositoryTest {

    static User user = new User();

    @Autowired
    UserRepository ur;

    @Test
    @Order(1)
    void create() {
        user.setFirstName("fname");
        user.setLastName("lname");
        user.setEmail("mail@mail");
        user.setPassword("pass");
        user.setPhone("0506693793");
        user.setUsername("user");
        user.setRole(Role.USER);
        ur.save(user);
        System.out.println(user.getId());
    }

    @Test
    @Order(2)
    void findById() {
        System.out.println(ur.findById(user.getId()));
    }

    @Test
    @Order(3)
    void findAll() {
        System.out.println(ur.findAll());
    }

    @Test
    @Order(4)
    void update() {
        user.setUsername("new Username");
        ur.save(user);
    }

    @Test
    @Order(5)
    void findByUsername() {
        System.out.println(ur.findByUsername(user.getUsername()));
    }

    @Test
    @Order(6)
    void findByEmail() {
        System.out.println(ur.findByEmail(user.getEmail()));
    }

    @Test
    @Order(7)
    void findByFirstNameAndLastName() {
        System.out.println(ur.findByFirstNameAndLastName(user.getFirstName(),
                user.getLastName()));
    }

    @Test
    @Order(8)
    void findByPhone() {
        System.out.println(ur.findByPhone(user.getPhone()));
    }

    @Test
    @Order(9)
    void delete() {
        ur.deleteById(user.getId());
    }
}
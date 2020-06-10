package com.cinema.point.repository;

import com.cinema.point.domain.Hall;
import com.cinema.point.domain.HallType;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HallRepositoryTest {

    static Hall hall = new Hall();
    static HallType hallType = HallType._IMAX;

    @Autowired
    HallRepository hr;

    @Test
    @Order(1)
    void create() {
        hall.setColumns(20);
        hall.setRows(20);
        hall.setFreePlaces(400);
        hall.setType(hallType);
        hr.save(hall);
    }

    @Test
    @Order(2)
    void findById() {
        System.out.println(hr.findById(hall.getId()));
    }

    @Test
    @Order(3)
    void update() {
        hall.reserve(20);
        hr.save(hall);
    }

    @Test
    @Order(4)
    void findFreePlaces() {
        System.out.println(hr.findFreePlaces(hall.getId()));
    }

    @Test
    @Order(5)
    void findReversedPlaces() {
        System.out.println(hr.findReversedPlaces(hall.getId()));
    }

    @Test
    @Order(6)
    void findByType() {
        System.out.println(hr.findByType(hallType));
    }

    @Test
    @Order(7)
    void delete() {
        hr.delete(hall);
        findById();
    }
}
package com.cinema.point.repository;

import com.cinema.point.domain.Movie;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MovieRepositoryTest {

    static Movie movie = new Movie();

    @Autowired
    MovieRepository mr;

    @Test
    @Order(1)
    void create() {
        movie.setName("movie");
        movie.setDescription("desc");
        movie.setDuration(123L);
        mr.save(movie);
        System.out.println(movie.getId());
    }

    @Test
    @Order(2)
    void findById() {
        System.out.println(mr.findById(movie.getId()));
    }

    @Test
    @Order(3)
    void findAll() {
        System.out.println(mr.findAll());
    }

    @Test
    @Order(4)
    void update() {
        movie.setDuration(12345L);
    }

    @Test
    @Order(5)
    void findByName() {
        System.out.println(mr.findByName(movie.getName()));
    }

    @Test
    @Order(6)
    void findByHallId() {
        //todo create this test
//        System.out.println(mr.findByHallId());
    }

    @Test
    @Order(7)
    void delete() {
        mr.delete(movie);
        System.out.println(movie.getId());
        findById();
    }

}
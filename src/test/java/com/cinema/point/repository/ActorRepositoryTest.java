package com.cinema.point.repository;

import com.cinema.point.domain.Actor;
import com.cinema.point.domain.Movie;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ActorRepositoryTest {

    static Actor actor = new Actor();
    static Movie movie = new Movie();

    @Autowired
    ActorRepository ar;

    @Autowired
    MovieRepository mr;

    @Test
    @Order(1)
    void create() {
        actor.setFirstName("actor F");
        actor.setLastName("actor L");
        ar.save(actor);
        Long actual = actor.getId();
        assertNotNull(actual);

        movie.setName("test movie");
        movie.setDescription("desc");
        movie.setDuration(123L);
        movie.addActor(actor);
        mr.save(movie);
        Long actual2 = movie.getId();
        assertNotNull(actual2);
    }

    @Test
    @Order(2)
    void findActor() {
        System.out.println(ar.findActor("f", "l"));
        System.out.println(ar.findActor(actor.getFirstName(),
                actor.getLastName()));
    }

    @Test
    @Order(3)
    void findAll() {
        Actor actor1 = new Actor();
        actor1.setFirstName("actor1");
        actor1.setLastName("actor1");
        ar.save(actor1);
        System.out.println(ar.findAll());
        ar.delete(actor1);
    }

    @Test
    @Order(4)
    void update() {
        actor.setLastName("another l name");
        ar.save(actor);
    }

    @Test
    @Order(5)
    void findById() {
        System.out.println(ar.findById(actor.getId()));
    }

    @Test
    @Order(6)
    void findByMovie() {
        System.out.println(ar.findByMovieId(movie.getId()));
    }

    @Test
    @Order(7)
    void delete() {
        System.out.println(actor.getId());
        System.out.println(actor.getFirstName());
        System.out.println(actor.getLastName());
        mr.delete(movie);
        ar.delete(actor);
    }
}
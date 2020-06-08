package com.cinema.point.repository;

import com.cinema.point.domain.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SeanceRepositoryTest {

    static Seance seance = new Seance();
    static Hall hall = new Hall();
    static Movie movie = new Movie();
    static Ticket ticket = new Ticket();

    @Autowired
    SeanceRepository sr;

    @Autowired
    MovieRepository mr;

    @Autowired
    HallRepository hr;

    @Autowired
    TicketRepository tr;

    @Test
    @Order(1)
    void create() {
        Set<Day> days = new HashSet<>();
        days.add(Day.MONDAY);
        days.add(Day.WEDNESDAY);
        seance.setDay(days);
        hall.setType(HallType._3D);
        hall.setRows(20);
        hall.setColumns(20);
        hall.setFreePlaces(400);
        seance.setHall(hall);
        movie.setName("movie");
        movie.setDescription("desc");
        movie.setDuration(1243L);
        seance.setMovie(movie);
        seance.setSeanceDateFrom(new Date(100L));
        seance.setSeanceDateTo(new Date(123L));
        seance.setMovieBeginTime(new Time(10L));
        seance.setMovieEndTime(new Time(20L));
        seance.setTicketPrice(new BigDecimal(14));
        hr.save(hall);
        mr.save(movie);
        sr.save(seance);
        ticket.setSeance(seance);
        ticket.setPlaceRow(10);
        ticket.setPlaceColumn(10);
        ticket.setSeanceDate(new Date(123L));
        tr.save(ticket);

    }

    @Test
    @Order(2)
    void findById() {
        System.out.println(sr.findById(seance.getId()));
    }

    @Test
    @Order(3)
    void findAll() {
        System.out.println(sr.findAll());
    }

    @Test
    @Order(4)
        //this working
    void updateFromExternal() {
        movie.setDescription("new movie description");
        mr.save(movie);
        findById();
    }

    @Test
    @Order(5)
        //not working
    void updateFromInternal() {
        hall.setType(HallType._IMAX);
        sr.save(seance);
        findById();
    }

    @Test
    @Order(6)
    void findBySeanceDateTo() {
        System.out.println(sr.findBySeanceDateTo(new Date(122L)));
        System.out.println(sr.findBySeanceDateTo(new Date(123L)));
    }

    @Test
    @Order(7)
    void findByTimeLineInSeason() {
        System.out.println(sr.findByTimeLineInSeason(new Time(9L)));
        System.out.println(sr.findByTimeLineInSeason(new Time(21L)));
        System.out.println(sr.findByTimeLineInSeason(new Time(15L)));
    }

    @Test
    @Order(8)
    void findByHall() {
        System.out.println(sr.findByHall(hall));
        System.out.println(sr.findByHall(seance.getHall()));
    }

    @Test
    @Order(9)
    void findByHallType() {
        System.out.println(sr.findByHallType(hall.getType()));
    }

    @Test
    @Order(10)
    void findByDay() {
        System.out.println(sr.findByDay(Day.FRIDAY));
        System.out.println(sr.findByDay(Day.MONDAY));
    }

    @Test
    @Order(11)
    void findByMovie() {
        System.out.println(sr.findByMovie(movie));
        System.out.println(sr.findByMovie(seance.getMovie()));
    }

    @Test
    @Order(12)
    void findByTicketId() {
        System.out.println(sr.findByTicketId(ticket.getId()));
    }

    @Test
    @Order(13)
    void delete() {
        tr.deleteAll();
        sr.deleteAll();
        hr.deleteAll();
        mr.deleteAll();
    }
}
//package com.cinema.point.repository;
//
//import com.cinema.point.domain.*;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.math.BigDecimal;
//import java.sql.Date;
//import java.sql.Time;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//class TicketRepositoryTest {
//
//    static Seance seance = new Seance();
//    static Hall hall = new Hall();
//    static Movie movie = new Movie();
//    static Ticket ticket = new Ticket();
//    static User user = new User();
//
//    @Autowired
//    SeanceRepository sr;
//
//    @Autowired
//    MovieRepository mr;
//
//    @Autowired
//    HallRepository hr;
//
//    @Autowired
//    UserRepository ur;
//
//    @Autowired
//    TicketRepository tr;
//
//    @Test
//    @Order(1)
//    void create() {
//        Set<Day> days = new HashSet<>();
//        days.add(Day.MONDAY);
//        days.add(Day.WEDNESDAY);
//        seance.setDay(days);
//        hall.setType(HallType._3D);
//        hall.setRows(20);
//        hall.setColumns(20);
//        hall.setFreePlaces(400);
//        seance.setHall(hall);
//        movie.setName("movie");
//        movie.setDescription("desc");
//        movie.setDuration(1243L);
//        seance.setMovie(movie);
//        seance.setSeanceDateFrom(new Date(100L));
//        seance.setSeanceDateTo(new Date(123L));
//        seance.setMovieBeginTime(new Time(10L));
//        seance.setMovieEndTime(new Time(20L));
//        seance.setTicketPrice(new BigDecimal(14));
//        hr.save(hall);
//        mr.save(movie);
//        sr.save(seance);
//
//        ticket.setSeance(seance);
//        ticket.setPlaceRow(10);
//        ticket.setPlaceColumn(10);
//        ticket.setSeanceDate(new Date(123L));
//        tr.save(ticket);
//        List<Ticket> tickets = new ArrayList<>();
//        tickets.add(ticket);
//
//        user.setFirstName("fname");
//        user.setLastName("lname");
//        user.setEmail("mail@mail");
//        user.setPassword("pass");
//        user.setPhone("0506693793");
//        user.setUsername("user");
//        user.setRole(Role.USER);
//        user.setTickets(tickets);
//        ur.save(user);
//    }
//
//    @Test
//    @Order(2)
//    void findById() {
//        Ticket ticket1 = tr.findById(ticket.getId()).get();
//        System.out.println(ticket1);
//    }
//
//    @Test
//    @Order(3)
//    void findAll() {
//        System.out.println(tr.findAll());
//    }
//
//    @Test
//    @Order(4)
//    void update() {
//        ticket.setPlaceRow(24);
//        tr.save(ticket);
//    }
//
////    @Test
////    @Order(5)
////    void findBySeance() {
////        System.out.println(tr.findBySeance(ticket.getSeance()));
////    }
//
//    @Test
//    @Order(6)
//    void findByUserId() {
//        System.out.println(tr.findByUserId(user.getId()));
//    }
//
//    @Test
//    @Order(7)
//    void delete() {
//        ur.deleteAll();
//        tr.deleteAll();
//        sr.deleteAll();
//        hr.deleteAll();
//        mr.deleteAll();
//    }
//}
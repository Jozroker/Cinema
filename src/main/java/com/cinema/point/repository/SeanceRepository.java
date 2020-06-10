package com.cinema.point.repository;

import com.cinema.point.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

@Repository
public interface SeanceRepository extends JpaRepository<Seance, Long> {

    List<Seance> findBySeanceDateTo(Date date);

    @Query("select s from Seance s where s.movieBeginTime < ?1 and s" +
            ".movieEndTime > ?1")
    Optional<Seance> findByTimeLineInSeance(Time time);

    //filters
    List<Seance> findByHall(Hall hall);

    //filters
    List<Seance> findByHallType(HallType type);

//    @Query("select s.movie from Seance s where s.hall = ?1")
//    List<Movie> findMoviesByHall(Hall hall);
//
//    @Query("select s.movie from Seance s where s.hall.type = ?1")
//    List<Movie> findMoviesByHallType(HallType type);

    @Query("select s from Seance s where ?1 member of s.day")
    List<Seance> findByDay(Day day);

    List<Seance> findByMovie(Movie movie);

    @Query("select t.seance from Ticket t where t.id = ?1")
    Optional<Seance> findByTicketId(Long id);
}

package com.cinema.point.repository;

import com.cinema.point.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findBySeanceId(Long id);

    @Query("select u.tickets from User u where u.id = ?1")
    List<Ticket> findByUserId(Long id);

    @Transactional
    @Modifying
    @Query("delete from Ticket t where t.seanceDate <= ?1 and t.seance in " +
            "(select s from Seance s where s.movieEndTime <= ?2)")
    void deleteTicketsByDate(Date date, Time time);

    @Transactional
    @Modifying
    void deleteBySeanceId(Long id);

}

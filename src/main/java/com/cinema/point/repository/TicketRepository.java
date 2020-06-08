package com.cinema.point.repository;

import com.cinema.point.domain.Seance;
import com.cinema.point.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

//    @Query("select u from User u where ?1 member of u.tickets")
//    Optional<User> findUserByTicket(Ticket ticket);

    List<Ticket> findBySeance(Seance seance);

    @Query("select u.tickets from User u where u.id = ?1")
    List<Ticket> findByUserId(Long id);

//    @Query("select t.seance from Ticket t")
//    Optional<Seance> findSeanceByTicket(Ticket ticket);
}

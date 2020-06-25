package com.cinema.point.repository;

import com.cinema.point.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findBySeanceId(Long id);

    @Query("select u.tickets from User u where u.id = ?1")
    List<Ticket> findByUserId(Long id);

}

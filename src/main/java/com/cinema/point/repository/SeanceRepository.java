package com.cinema.point.repository;

import com.cinema.point.domain.Day;
import com.cinema.point.domain.HallType;
import com.cinema.point.domain.Seance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

@Repository
public interface SeanceRepository extends JpaRepository<Seance, Long> {

    @Query("select s from Seance s where s.seanceDateTo <= ?1")
    List<Seance> findBySeanceDateTo(Date date);

    @Query("select s from Seance s where (s.seanceDateFrom >= ?1 and s" +
            ".seanceDateTo <= ?2) or (?1 between s.seanceDateFrom and s" +
            ".seanceDateTo) or (?2 between s.seanceDateFrom and s" +
            ".seanceDateTo)")
    List<Seance> findBySeanceDates(Date dateFrom, Date dateTo);

    @Query("select s from Seance s where ?1 between s.seanceDateFrom and s.seanceDateTo")
    List<Seance> findByDateBetween(Date date);

    @Query("select s from Seance s where s.movieBeginTime <= ?1 and s" +
            ".movieEndTime >= ?1")
    List<Seance> findByTimeLineInSeance(Time time);

    @Query("select s from Seance s where s.hall.id = ?1")
    List<Seance> findByHallId(Long id);

    List<Seance> findByHallType(HallType type);

    @Query("select s from Seance s where ?1 member of s.day")
    List<Seance> findByDay(Day day);

    List<Seance> findByMovieId(Long id);

    @Query("select t.seance from Ticket t where t.id = ?1")
    Optional<Seance> findByTicketId(Long id);

    @Transactional
    @Modifying
//    @Query("delete from Seance s where s.movie in (select m from Movie m " +
//            "where m.id = ?1)")
    void deleteByMovieId(Long id);

    @Transactional
    @Modifying
    @Query(value = "delete from seance_day where seance_id = ?1", nativeQuery = true)
    void deleteDays(Long id);
}

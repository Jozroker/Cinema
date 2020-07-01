package com.cinema.point.repository;

import com.cinema.point.domain.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {

    @Query("select a from Actor a where a.firstName = ?1 and a.lastName = ?2")
    Optional<Actor> findActor(String firstName, String lastName);

    @Query("select m.actors from Movie m where m.id = ?1")
    List<Actor> findByMovieId(Long id);
}

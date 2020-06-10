package com.cinema.point.repository;

import com.cinema.point.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    Optional<Movie> findByName(String name);

//    @Query("select m.actors from Movie m where m.id = ?1")
//    List<Actor> findActors(Long id);

    //unused
    @Query("select m from Seance s join s.movie m where s.hall.id = ?1")
    List<Movie> findByHallId(Long id);
}

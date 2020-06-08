package com.cinema.point.repository;

import com.cinema.point.domain.Hall;
import com.cinema.point.domain.HallType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HallRepository extends JpaRepository<Hall, Long> {

    @Query("select h.freePlaces from Hall h where h.id = ?1")
    Integer findFreePlaces(Long id);

    @Query("select h.reservedPlaces from Hall h where h.id = ?1")
    Integer findReversedPlaces(Long id);

    List<Hall> findByType(HallType type);
}

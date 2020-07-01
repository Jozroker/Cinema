package com.cinema.point.repository;

import com.cinema.point.domain.Hall;
import com.cinema.point.domain.HallType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HallRepository extends JpaRepository<Hall, Long> {

    List<Hall> findByType(HallType type);
}

package com.cinema.point.service;

import com.cinema.point.domain.Hall;
import com.cinema.point.domain.HallType;

import java.util.List;

public interface HallService {

//    Integer findFreePlaces(Long id);
//
//    Integer findReversedPlaces(Long id);

    List<Hall> findByType(HallType type);

    Hall findById(Long id);
}

package com.cinema.point.service;

import com.cinema.point.domain.Hall;
import com.cinema.point.domain.HallType;

import java.util.List;

public interface HallService {

    List<Hall> findByType(HallType type);

    Hall findById(Long id);
}

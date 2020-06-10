package com.cinema.point.service.impl;

import com.cinema.point.domain.Hall;
import com.cinema.point.domain.HallType;
import com.cinema.point.errors.ResoursNotFoundException;
import com.cinema.point.repository.HallRepository;
import com.cinema.point.service.HallService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class HallServiceImpl implements HallService {

    private final HallRepository hallRepository;

    public HallServiceImpl(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    @Override
    public Integer findFreePlaces(Long id) {
        log.debug("finding free places count in hall by id " + id);
        return hallRepository.findFreePlaces(id).orElseThrow(() -> new ResoursNotFoundException("Hall", id));
    }

    @Override
    public Integer findReversedPlaces(Long id) {
        log.debug("finding reserved places count in hall by id " + id);
        return hallRepository.findReversedPlaces(id).orElseThrow(() -> new ResoursNotFoundException("Hall", id));
    }

    @Override
    public List<Hall> findByType(HallType type) {
        return hallRepository.findByType(type);
    }
}

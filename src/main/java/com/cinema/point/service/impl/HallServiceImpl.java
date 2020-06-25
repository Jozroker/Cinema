package com.cinema.point.service.impl;

import com.cinema.point.domain.Hall;
import com.cinema.point.domain.HallType;
import com.cinema.point.errors.ResourceNotFoundException;
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
    public List<Hall> findByType(HallType type) {
        return hallRepository.findByType(type);
    }

    @Override
    public Hall findById(Long id) {
        return hallRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hall", id));
    }
}

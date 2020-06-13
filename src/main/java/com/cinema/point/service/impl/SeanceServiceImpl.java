package com.cinema.point.service.impl;

import com.cinema.point.domain.*;
import com.cinema.point.dto.SeanceCreationDTO;
import com.cinema.point.dto.SeanceDTO;
import com.cinema.point.errors.ResourceNotFoundException;
import com.cinema.point.repository.HallRepository;
import com.cinema.point.repository.MovieRepository;
import com.cinema.point.repository.SeanceRepository;
import com.cinema.point.service.SeanceService;
import com.cinema.point.service.mapper.SeanceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SeanceServiceImpl implements SeanceService {

    SeanceRepository seanceRepository;
    HallRepository hallRepository;
    MovieRepository movieRepository;
    SeanceMapper seanceMapper;

    public SeanceServiceImpl(SeanceRepository seanceRepository, HallRepository hallRepository, MovieRepository movieRepository, SeanceMapper seanceMapper) {
        this.seanceRepository = seanceRepository;
        this.hallRepository = hallRepository;
        this.movieRepository = movieRepository;
        this.seanceMapper = seanceMapper;
    }

    @Override
    @Transactional
    public SeanceCreationDTO create(SeanceCreationDTO seanceDTO) {
        log.debug("creating new seance {}", seanceDTO);
        Seance seance = seanceMapper.toEntity(seanceDTO);
        Hall hall = hallRepository.findById(seanceDTO.getHallId())
                .orElseThrow(() -> new ResourceNotFoundException("Hall",
                        seanceDTO.getHallId()));
        Movie movie = movieRepository.findById(seanceDTO.getMovieId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie",
                        seanceDTO.getMovieId()));
        seance.setHall(hall);
        seance.setMovie(movie);
        return seanceMapper.toCreationDTO(seanceRepository.save(seance));
    }

    @Override
    public void deleteById(Long id) {
        log.debug("deleting seance by id {}", id);
        seanceRepository.deleteById(id);
    }

    @Override
    @Transactional
    public SeanceDTO update(SeanceCreationDTO seanceDTO) {
        log.debug("updating seance {}", seanceDTO);
        Seance seance = seanceMapper.toEntity(seanceDTO);
        Hall hall = hallRepository.findById(seanceDTO.getHallId())
                .orElseThrow(() -> new ResourceNotFoundException("Hall",
                        seanceDTO.getHallId()));
        Movie movie = movieRepository.findById(seanceDTO.getMovieId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie",
                        seanceDTO.getMovieId()));
        seance.setHall(hall);
        seance.setMovie(movie);
        return seanceMapper.toDTO(seanceRepository.save(seance));
    }

    @Override
    public List<SeanceCreationDTO> findCreationAll() {
        log.debug("finding all seances: to creation seance");
        return seanceRepository.findAll().stream().map(seanceMapper::toCreationDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SeanceDTO> findAll() {
        log.debug("finding all seances: to seance");
        return seanceRepository.findAll().stream().map(seanceMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SeanceDTO findById(Long id) {
        log.debug("finding seance by id {}", id);
        return seanceMapper.toDTO(seanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Seance", id)));
    }

    @Override
    public List<SeanceCreationDTO> findBySeanceDateTo(Date date) {
        log.debug("finding seances by date to {}", date);
        return seanceRepository.findBySeanceDateTo(date).stream()
                .map(seanceMapper::toCreationDTO).collect(Collectors.toList());
    }

    @Override
    public SeanceDTO findByTimeLineInSeance(Time time) {
        log.debug("finding seance by time {}", time);
        return seanceMapper.toDTO(seanceRepository.findByTimeLineInSeance(time)
                .orElseThrow(() -> new ResourceNotFoundException("Seance",
                        "(time) " + time)));
    }

    @Override
    public List<SeanceDTO> findByHall(Hall hall) {
        log.debug("finding seances by hall {}", hall);
        return seanceRepository.findByHall(hall).stream()
                .map(seanceMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<SeanceDTO> findByHallType(HallType hallType) {
        log.debug("finding seances by hall type {}", hallType);
        return seanceRepository.findByHallType(hallType).stream()
                .map(seanceMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<SeanceDTO> findByDay(Day day) {
        log.debug("finding seances by day {}", day);
        return seanceRepository.findByDay(day).stream()
                .map(seanceMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<SeanceDTO> findByMovie(Movie movie) {
        log.debug("finding seances by movie {}", movie);
        return seanceRepository.findByMovie(movie).stream()
                .map(seanceMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public SeanceDTO findByTicketId(Long id) {
        log.debug("finding seance by ticket id {}", id);
        return seanceMapper.toDTO(seanceRepository.findByTicketId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket", id)));
    }
}

package com.cinema.point.service.impl;

import com.cinema.point.domain.*;
import com.cinema.point.dto.SeanceCreationDTO;
import com.cinema.point.dto.SeanceDTO;
import com.cinema.point.errors.ResourceNotFoundException;
import com.cinema.point.repository.HallRepository;
import com.cinema.point.repository.MovieRepository;
import com.cinema.point.repository.SeanceRepository;
import com.cinema.point.service.SeanceService;
import com.cinema.point.service.TicketService;
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

    private final SeanceRepository seanceRepository;
    private final HallRepository hallRepository;
    private final MovieRepository movieRepository;
    private final SeanceMapper seanceMapper;
    private final TicketService ticketService;

    public SeanceServiceImpl(SeanceRepository seanceRepository,
                             HallRepository hallRepository,
                             MovieRepository movieRepository,
                             SeanceMapper seanceMapper,
                             TicketService ticketService) {
        this.seanceRepository = seanceRepository;
        this.hallRepository = hallRepository;
        this.movieRepository = movieRepository;
        this.seanceMapper = seanceMapper;
        this.ticketService = ticketService;
    }

    @Override
    @Transactional
    public SeanceCreationDTO save(SeanceCreationDTO seanceDTO) {
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
    public SeanceCreationDTO findCreationById(Long id) {
        log.debug("finding seance creation by id {}", id);
        return seanceMapper.toCreationDTO(seanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Seance", id)));
    }

    @Override
    public void deleteById(Long id) {
        log.debug("deleting seance by id {}", id);
        ticketService.findBySeanceId(id).forEach(ticket -> {
            ticketService.deleteById(ticket.getId());
        });
        deleteDays(id);
        seanceRepository.deleteById(id);
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
    public List<SeanceCreationDTO> findBySeanceDates(Date dateFrom, Date dateTo) {
        log.debug("finding seances by date from {} and date to {}", dateFrom,
                dateTo);
        return seanceRepository.findBySeanceDates(dateFrom, dateTo).stream()
                .map(seanceMapper::toCreationDTO).collect(Collectors.toList());
    }

    @Override
    public List<SeanceCreationDTO> findByTimeLineInSeance(Time time) {
        log.debug("finding seance by time {}", time);
        return seanceRepository.findByTimeLineInSeance(time).stream()
                .map(seanceMapper::toCreationDTO).collect(Collectors.toList());
    }

    @Override
    public List<SeanceDTO> findByHallId(Long id) {
        log.debug("finding seances by hall id {}", id);
        return seanceRepository.findByHallId(id).stream()
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
    public List<SeanceDTO> findByMovieId(Long id) {
        log.debug("finding seances by movie id {}", id);
        return seanceRepository.findByMovieId(id).stream()
                .map(seanceMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<SeanceCreationDTO> findCreationByMovieId(Long id) {
        log.debug("finding seances by movie id {}", id);
        return seanceRepository.findByMovieId(id).stream()
                .map(seanceMapper::toCreationDTO).collect(Collectors.toList());
    }

    @Override
    public SeanceDTO findByTicketId(Long id) {
        log.debug("finding seance by ticket id {}", id);
        return seanceMapper.toDTO(seanceRepository.findByTicketId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket", id)));
    }

    @Override
    public List<SeanceDTO> findByDateBetween(Date date) {
        log.debug("finding seances by date between {}", date);
        return seanceRepository.findByDateBetween(date).stream()
                .map(seanceMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<SeanceCreationDTO> findCreationByDateBetween(Date date) {
        log.debug("finding seances creation by date between {}", date);
        return seanceRepository.findByDateBetween(date).stream()
                .map(seanceMapper::toCreationDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteByMovieId(Long id) {
        log.debug("deleting seances by movie id {}", id);
        seanceRepository.findByMovieId(id).forEach(seance -> {
            deleteDays(seance.getId());
            ticketService.deleteBySeanceId(seance.getId());
        });
        seanceRepository.deleteByMovieId(id);
    }

    @Override
    public void deleteDays(Long id) {
        log.debug("deleting values from seance_day table by seance id {}", id);
        seanceRepository.deleteDays(id);
    }
}

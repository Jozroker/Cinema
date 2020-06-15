package com.cinema.point.service;

import com.cinema.point.domain.Day;
import com.cinema.point.domain.Hall;
import com.cinema.point.domain.HallType;
import com.cinema.point.domain.Movie;
import com.cinema.point.dto.SeanceCreationDTO;
import com.cinema.point.dto.SeanceDTO;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface SeanceService {

    SeanceCreationDTO create(SeanceCreationDTO seanceDTO);

    SeanceDTO update(SeanceCreationDTO seanceDTO);

    void deleteById(Long id);

    List<SeanceCreationDTO> findCreationAll();

    List<SeanceDTO> findAll();

    SeanceDTO findById(Long id);

    List<SeanceDTO> findByDateBetween(Date date);

    List<SeanceCreationDTO> findBySeanceDateTo(Date date);

    SeanceDTO findByTimeLineInSeance(Time time);

    List<SeanceDTO> findByHall(Hall hall);

    List<SeanceDTO> findByHallType(HallType hallType);

    List<SeanceDTO> findByDay(Day day);

    List<SeanceDTO> findByMovie(Movie movie);

    SeanceDTO findByTicketId(Long id);
}

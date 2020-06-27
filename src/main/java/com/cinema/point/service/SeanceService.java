package com.cinema.point.service;

import com.cinema.point.domain.Day;
import com.cinema.point.domain.HallType;
import com.cinema.point.dto.SeanceCreationDTO;
import com.cinema.point.dto.SeanceDTO;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface SeanceService {

    SeanceCreationDTO save(SeanceCreationDTO seanceDTO);

    void deleteById(Long id);

    List<SeanceCreationDTO> findCreationAll();

    List<SeanceDTO> findAll();

    SeanceCreationDTO findCreationById(Long id);

    SeanceDTO findById(Long id);

    List<SeanceDTO> findByDateBetween(Date date);

    List<SeanceCreationDTO> findCreationByDateBetween(Date date);

    List<SeanceCreationDTO> findBySeanceDateTo(Date date);

    List<SeanceCreationDTO> findBySeanceDates(Date dateFrom, Date dateTo);

    List<SeanceCreationDTO> findByTimeLineInSeance(Time time);

    List<SeanceDTO> findByHallId(Long id);

    List<SeanceDTO> findByHallType(HallType hallType);

    List<SeanceDTO> findByDay(Day day);

    List<SeanceDTO> findByMovieId(Long id);

    List<SeanceCreationDTO> findCreationByMovieId(Long id);

    SeanceDTO findByTicketId(Long id);
}

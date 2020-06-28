package com.cinema.point.service;

import com.cinema.point.dto.TicketDTO;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Map;

public interface TicketService {

    TicketDTO create(TicketDTO ticketDTO);

    TicketDTO addTicketToUser(TicketDTO ticketDTO, Long userId);

    void deleteById(Long id);

    void deleteByDate(Date date, Time time);

    void deleteBySeanceId(Long id);

    List<TicketDTO> findAll();

    TicketDTO findById(Long id);

    List<TicketDTO> findBySeanceId(Long id);

    List<TicketDTO> findByUserId(Long id);

    Map<Integer, Integer> findReservedPlaces(List<TicketDTO> tickets);

    Map<Integer, Integer> findPlacesByHallId(Long id);
}

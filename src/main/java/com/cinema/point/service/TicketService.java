package com.cinema.point.service;

import com.cinema.point.dto.TicketDTO;

import java.util.List;

public interface TicketService {

    TicketDTO create(TicketDTO ticketDTO);

    void deleteById(Long id);

    List<TicketDTO> findAll();

    TicketDTO findById(Long id);

    List<TicketDTO> findBySeanceId(Long id);

    List<TicketDTO> findByUserId(Long id);
}

package com.cinema.point.service.impl;

import com.cinema.point.domain.Ticket;
import com.cinema.point.dto.TicketDTO;
import com.cinema.point.errors.ResourceNotFoundException;
import com.cinema.point.repository.TicketRepository;
import com.cinema.point.service.TicketService;
import com.cinema.point.service.mapper.TicketMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TicketServiceImpl implements TicketService {

    TicketRepository ticketRepository;
    TicketMapper ticketMapper;

    public TicketServiceImpl(TicketRepository ticketRepository, TicketMapper ticketMapper) {
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
    }

    @Override
    public TicketDTO create(TicketDTO ticketDTO) {
        log.debug("creating new ticket {}", ticketDTO);
        Ticket ticket = ticketMapper.toEntity(ticketDTO);
        return ticketMapper.toDTO(ticketRepository.save(ticket));
    }

    @Override
    public void deleteById(Long id) {
        log.debug("deleting ticket by id {}", id);
        ticketRepository.deleteById(id);
    }

    @Override
    public List<TicketDTO> findAll() {
        log.debug("finding all tickets");
        return ticketRepository.findAll().stream()
                .map(ticketMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public TicketDTO findById(Long id) {
        log.debug("finding ticket by id {}", id);
        return ticketMapper.toDTO(ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket", id)));
    }

    @Override
    public List<TicketDTO> findBySeanceId(Long id) {
        log.debug("finding tickets by seance id {}", id);
        return ticketRepository.findBySeanceId(id).stream()
                .map(ticketMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<TicketDTO> findByUserId(Long id) {
        log.debug("finding tickets by user id {}", id);
        return ticketRepository.findByUserId(id).stream()
                .map(ticketMapper::toDTO).collect(Collectors.toList());
    }
}

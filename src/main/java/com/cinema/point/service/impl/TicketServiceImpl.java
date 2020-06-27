package com.cinema.point.service.impl;

import com.cinema.point.domain.Hall;
import com.cinema.point.domain.Seance;
import com.cinema.point.domain.Ticket;
import com.cinema.point.domain.User;
import com.cinema.point.dto.TicketDTO;
import com.cinema.point.errors.ResourceNotFoundException;
import com.cinema.point.repository.HallRepository;
import com.cinema.point.repository.SeanceRepository;
import com.cinema.point.repository.TicketRepository;
import com.cinema.point.service.TicketService;
import com.cinema.point.service.UserService;
import com.cinema.point.service.mapper.TicketMapper;
import com.cinema.point.service.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    private final UserMapper userMapper;
    private final UserService userService;
    private final SeanceRepository seanceRepository;
    private final HallRepository hallRepository;

    public TicketServiceImpl(TicketRepository ticketRepository,
                             TicketMapper ticketMapper,
                             UserMapper userMapper,
                             UserService userService,
                             SeanceRepository seanceRepository,
                             HallRepository hallRepository) {
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
        this.userMapper = userMapper;
        this.userService = userService;
        this.seanceRepository = seanceRepository;
        this.hallRepository = hallRepository;
    }

    @Override
    @Transactional
    public TicketDTO create(TicketDTO ticketDTO) {
        log.debug("creating new ticket {}", ticketDTO);
        Ticket ticket = ticketMapper.toEntity(ticketDTO);
        Seance seance = seanceRepository.findById(ticketDTO.getSeanceId())
                .orElseThrow(() -> new ResourceNotFoundException("Seance",
                        ticketDTO.getSeanceId()));
        ticket.setSeance(seance);
        ticket = ticketRepository.save(ticket);
        return ticketMapper.toDTO(ticket);
    }

    @Override
    @Transactional
    public TicketDTO addTicketToUser(TicketDTO ticketDTO, Long userId) {
        log.debug("creating new ticket {} by user with id {}", ticketDTO,
                userId);
        User user =
                userMapper.toEntity(userService.findById(userId));
        ticketDTO = create(ticketDTO);
        Ticket ticket = ticketMapper.toEntity(ticketDTO);
        Seance seance = seanceRepository.findById(ticketDTO.getSeanceId())
                .orElseThrow(() -> new ResourceNotFoundException("Seance", ticket.getSeance().getId()));
        ticket.setSeance(seance);
        user.addTicket(ticket);
        userService.save(userMapper.toDTO(user));
        return ticketDTO;
    }

    @Override
    public void deleteById(Long id) {
        log.debug("deleting ticket by id {}", id);
        ticketRepository.deleteById(id);
    }

    @Override
    public void deleteByDate(Date date, Time time) {
        log.debug("deleting ticket where date less then {} and time less then" +
                " {}", date, time);
        ticketRepository.deleteTicketsByDate(date, time);
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

    @Override
    public Map<Integer, Integer> findReservedPlaces(List<TicketDTO> tickets) {
        log.debug("finding reserved rows and columns by tickets");
        Map<Integer, Integer> map = new HashMap<>();
        tickets.forEach(t -> map.put(t.getRow(), t.getColumn()));
        return map;
    }

    @Override
    @Transactional
    public Map<Integer, Integer> findPlacesByHallId(Long id) {
        log.debug("finding rows and columns count by hall id {}", id);
        Map<Integer, Integer> map = new HashMap<>();
        Hall hall =
                hallRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hall", id));
        map.put(hall.getRows(), hall.getColumns());
        return map;
    }
}

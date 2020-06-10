package com.cinema.point.service.mapper;

import com.cinema.point.domain.Ticket;
import com.cinema.point.dto.TicketDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TicketMapper {

    @Mapping(source = "seance.id", target = "seanceId")
    TicketDTO toDTO(Ticket ticket);

    @Mapping(source = "seanceId", target = "seance.id")
    Ticket toEntity(TicketDTO ticketDTO);
}

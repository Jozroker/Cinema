package com.cinema.point.service.mapper;

import com.cinema.point.domain.Ticket;
import com.cinema.point.dto.TicketDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    @Mapping(source = "seance.id", target = "seanceId")
    @Mapping(source = "placeRow", target = "row")
    @Mapping(source = "placeColumn", target = "column")
    TicketDTO toDTO(Ticket ticket);

    @Mapping(source = "seanceId", target = "seance.id")
    @Mapping(source = "row", target = "placeRow")
    @Mapping(source = "column", target = "placeColumn")
    Ticket toEntity(TicketDTO ticketDTO);
}

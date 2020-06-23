package com.cinema.point.service.mapper;

import com.cinema.point.domain.Ticket;
import com.cinema.point.domain.User;
import com.cinema.point.dto.LoginUserDTO;
import com.cinema.point.dto.RegisterUserDTO;
import com.cinema.point.dto.UserDTO;
import com.cinema.point.errors.ResourceNotFoundException;
import com.cinema.point.repository.TicketRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Autowired
    TicketRepository ticketRepository;

    @Mapping(source = "tickets", target = "ticketsId", qualifiedByName =
            "mapToTicketsIds")
    @Mapping(source = "picture", target = "pictureString",
            qualifiedByName = "mapToStringPicture")
    public abstract UserDTO toDTO(User user);

    //unused
//    @Mapping(source = "ticketsId", target = "tickets.id")
//    User toEntity(UserDTO userDTO);

    public abstract User toEntity(LoginUserDTO userDTO);

    @Mapping(source = "pictureString", target = "picture",
            qualifiedByName = "mapToBytePicture")
    public abstract User toEntity(RegisterUserDTO userDTO);

    @Mapping(source = "pictureString", target = "picture",
            qualifiedByName = "mapToBytePicture")
    @Mapping(source = "ticketsId", target = "tickets", qualifiedByName =
            "mapToTickets")
    public abstract User toEntity(UserDTO userDTO);

    List<Long> mapToTicketsIds(List<Ticket> tickets) {
        return tickets.stream().map(Ticket::getId).collect(Collectors.toList());
    }

    List<Ticket> mapToTickets(List<Long> tickets) {
        return tickets.stream().map(id -> ticketRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Actor", id))).collect(Collectors.toList());
    }

    String mapToStringPicture(byte[] picture) {
        return new String(picture, StandardCharsets.UTF_8);
    }

    byte[] mapToBytePicture(String string) {
        return string.getBytes(StandardCharsets.UTF_8);
    }

//    default byte[] mapToBase64Picture(byte[] picture) {
//        return Base64.decodeBase64(picture);
//    }
}

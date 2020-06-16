package com.cinema.point.service.mapper;

import com.cinema.point.domain.Ticket;
import com.cinema.point.domain.User;
import com.cinema.point.dto.LoginUserDTO;
import com.cinema.point.dto.RegisterUserDTO;
import com.cinema.point.dto.UserDTO;
import org.apache.tomcat.util.codec.binary.Base64;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "tickets", target = "ticketsId", qualifiedByName =
            "mapToTicketsIds")
    @Mapping(source = "picture", target = "pictureString",
            qualifiedByName = "mapToStringPicture")
    UserDTO toDTO(User user);

    //unused
//    @Mapping(source = "ticketsId", target = "tickets.id")
//    User toEntity(UserDTO userDTO);

    User toEntity(LoginUserDTO userDTO);

    @Mapping(source = "picture", target = "picture",
            qualifiedByName = "mapToBytePicture")
    User toEntity(RegisterUserDTO userDTO);

    User toEntity(UserDTO userDTO);

    default List<Long> mapToTicketsIds(List<Ticket> tickets) {
        return tickets.stream().map(Ticket::getId).collect(Collectors.toList());
    }

    default String mapToStringPicture(byte[] picture) {
        return new String(picture, StandardCharsets.UTF_8);
    }

    default byte[] mapToBytePicture(byte[] picture) {
        return Base64.encodeBase64(picture);
    }
}

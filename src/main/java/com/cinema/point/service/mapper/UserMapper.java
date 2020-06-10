package com.cinema.point.service.mapper;

import com.cinema.point.domain.User;
import com.cinema.point.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    //    @Mapping(source = "tickets.id", target = "ticketsId")
    UserDTO toDTO(User user);

    //    @Mapping(source = "ticketsId", target = "tickets.id")
    User toEntity(UserDTO userDTO);
}

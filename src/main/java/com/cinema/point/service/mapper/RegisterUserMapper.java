package com.cinema.point.service.mapper;

import com.cinema.point.domain.User;
import com.cinema.point.dto.RegisterUserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegisterUserMapper {

    RegisterUserDTO toDTO(User user);

    User toEntity(RegisterUserDTO userDTO);
}

package com.cinema.point.service.mapper;

import com.cinema.point.domain.User;
import com.cinema.point.dto.LoginUserDTO;
import org.mapstruct.Mapper;

@Mapper
public interface LoginUserMapper {

    LoginUserDTO toDTO(User user);

    User toEntity(LoginUserDTO userDTO);
}

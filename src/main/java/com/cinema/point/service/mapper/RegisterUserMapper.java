package com.cinema.point.service.mapper;

import com.cinema.point.domain.User;
import com.cinema.point.dto.RegisterUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.nio.charset.StandardCharsets;

@Mapper(componentModel = "spring")
public interface RegisterUserMapper {

    @Mapping(source = "picture", target = "pictureString",
            qualifiedByName = "mapToStringPicture")
    RegisterUserDTO toDTO(User user);

    @Mapping(source = "pictureString", target = "picture",
            qualifiedByName = "mapToBytePicture")
    User toEntity(RegisterUserDTO userDTO);

    default String mapToStringPicture(byte[] picture) {
        return new String(picture, StandardCharsets.UTF_8);
    }

    default byte[] mapToBytePicture(String string) {
        return string.getBytes(StandardCharsets.UTF_8);
    }
}

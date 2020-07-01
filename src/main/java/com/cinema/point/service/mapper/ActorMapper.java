package com.cinema.point.service.mapper;

import com.cinema.point.domain.Actor;
import com.cinema.point.dto.ActorDTO;
import org.apache.tomcat.util.codec.binary.Base64;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.nio.charset.StandardCharsets;

@Mapper(componentModel = "spring")
public interface ActorMapper {

    @Mapping(source = "profilePicture", target = "pictureString",
            qualifiedByName = "mapToStringPicture")
    ActorDTO toDTO(Actor actor);

    @Mapping(source = "profilePicture", target = "profilePicture",
            qualifiedByName = "mapToBytePicture")
    Actor toEntity(ActorDTO actorDTO);

    default String mapToStringPicture(byte[] picture) {
        return new String(picture, StandardCharsets.UTF_8);
    }

    default byte[] mapToBytePicture(byte[] picture) {
        return Base64.encodeBase64(picture);
    }
}

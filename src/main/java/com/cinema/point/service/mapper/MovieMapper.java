package com.cinema.point.service.mapper;

import com.cinema.point.domain.Actor;
import com.cinema.point.domain.Movie;
import com.cinema.point.dto.MovieDTO;
import com.cinema.point.dto.SimpleMovieDTO;
import com.cinema.point.errors.ResourceNotFoundException;
import com.cinema.point.repository.ActorRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class MovieMapper {

    @Autowired
    ActorRepository actorRepository;

    @Mapping(source = "actors", target = "actorsIds", qualifiedByName =
            "mapToActorsIds")
    @Mapping(source = "picture", target = "pictureString",
            qualifiedByName = "mapToStringPicture")
    public abstract MovieDTO toDTO(Movie movie);

    @Mapping(source = "actorsIds", target = "actors", qualifiedByName =
            "mapToActors")
    @Mapping(source = "picture", target = "picture",
            qualifiedByName = "mapToBytePicture")
    public abstract Movie toEntity(MovieDTO movieDTO);

    @Mapping(source = "picture", target = "pictureString",
            qualifiedByName = "mapToStringPicture")
    public abstract SimpleMovieDTO toSimpleDTO(Movie movie);

    @Mapping(source = "picture", target = "picture",
            qualifiedByName = "mapToBytePicture")
    public abstract Movie toEntity(SimpleMovieDTO movieDTO);

    Set<Long> mapToActorsIds(Set<Actor> actors) {
        return actors.stream().map(Actor::getId).collect(Collectors.toSet());
    }

    Set<Actor> mapToActors(Set<Long> actorsIds) {
        return actorsIds.stream().map(id -> actorRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Actor", id))).collect(Collectors.toSet());
    }

    String mapToStringPicture(byte[] picture) {
        return new String(picture, StandardCharsets.UTF_8);
    }

    byte[] mapToBytePicture(byte[] picture) {
        return Base64.encodeBase64(picture);
    }

}


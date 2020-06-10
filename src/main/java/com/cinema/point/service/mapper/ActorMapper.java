package com.cinema.point.service.mapper;

import com.cinema.point.domain.Actor;
import com.cinema.point.dto.ActorDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActorMapper {

    ActorDTO toDTO(Actor actor);

    Actor toEntity(ActorDTO actorDTO);
}

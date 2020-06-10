package com.cinema.point.service.mapper;

import com.cinema.point.domain.Seance;
import com.cinema.point.dto.SeanceCreationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SeanceCreationMapper {

    @Mapping(source = "hall.id", target = "hallId")
    @Mapping(source = "movie.id", target = "movieId")
    SeanceCreationDTO toDTO(Seance seance);

    @Mapping(source = "hallId", target = "hall.id")
    @Mapping(source = "movieId", target = "movie.id")
    Seance toEntity(SeanceCreationDTO seanceDTO);
}

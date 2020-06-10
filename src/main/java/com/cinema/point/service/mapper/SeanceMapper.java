package com.cinema.point.service.mapper;

import com.cinema.point.domain.Seance;
import com.cinema.point.dto.SeanceCreationDTO;
import com.cinema.point.dto.SeanceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface SeanceMapper {

    @Mapping(source = "hall.id", target = "hallId")
    @Mapping(source = "movie.id", target = "movieId")
    SeanceDTO toDTO(Seance seance);

    @Mapping(source = "hallId", target = "hall.id")
    @Mapping(source = "movieId", target = "movie.id")
    Seance toEntity(SeanceDTO seanceDTO);

    @Mapping(source = "hall.id", target = "hallId")
    @Mapping(source = "movie.id", target = "movieId")
    SeanceCreationDTO toCreationDTO(Seance seance);

    @Mapping(source = "hallId", target = "hall.id")
    @Mapping(source = "movieId", target = "movie.id")
    Seance toEntity(SeanceCreationDTO seanceDTO);
}

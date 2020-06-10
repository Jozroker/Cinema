package com.cinema.point.service.mapper;

import com.cinema.point.domain.Movie;
import com.cinema.point.dto.SimpleMovieDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SimpleMovieMapper {

    SimpleMovieDTO toDTO(Movie movie);

    Movie toEntity(SimpleMovieDTO movieDTO);
}

package com.cinema.point.service.mapper;

import com.cinema.point.domain.Movie;
import com.cinema.point.dto.MovieDTO;
import com.cinema.point.dto.SimpleMovieDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    //    @Mapping(source = "actors", target = "actorsId")
    MovieDTO toDTO(Movie movie);

    //
//    @Mapping(source = "actorsId", target = "actors")
    Movie toEntity(MovieDTO movieDTO);

    SimpleMovieDTO toSimpleDTO(Movie movie);

    Movie toEntity(SimpleMovieDTO movieDTO);
}

package com.cinema.point.service;

import com.cinema.point.dto.MovieDTO;
import com.cinema.point.dto.SimpleMovieDTO;

import java.util.List;

public interface MovieService {

    MovieDTO create(MovieDTO movieDTO);

    void deleteById(Long id);

    MovieDTO findById(Long id);

    SimpleMovieDTO findSimpleById(Long id);

    MovieDTO findByName(String name);

    SimpleMovieDTO findSimpleByName(String name);

    List<MovieDTO> findAll();

    List<SimpleMovieDTO> findSimpleAll();
}

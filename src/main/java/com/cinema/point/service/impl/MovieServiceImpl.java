package com.cinema.point.service.impl;

import com.cinema.point.domain.Movie;
import com.cinema.point.dto.MovieDTO;
import com.cinema.point.dto.SimpleMovieDTO;
import com.cinema.point.errors.ResourceNotFoundException;
import com.cinema.point.repository.MovieRepository;
import com.cinema.point.service.MovieService;
import com.cinema.point.service.mapper.MovieMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MovieServiceImpl implements MovieService {

    MovieRepository movieRepository;
    MovieMapper movieMapper;

    public MovieServiceImpl(MovieRepository movieRepository, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }

    @Override
    public MovieDTO create(MovieDTO movieDTO) {
        log.debug("creating movie {}", movieDTO);
        Movie movie = movieMapper.toEntity(movieDTO);
        return movieMapper.toDTO(movieRepository.save(movie));
    }

    @Override
    public void deleteById(Long id) {
        log.debug("deleting movie by id {}", id);
        movieRepository.deleteById(id);
    }

    @Override
    public MovieDTO findById(Long id) {
        log.debug("finding movie by id {}: to movie dto", id);
        return movieMapper.toDTO(movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", id)));
    }

    @Override
    public SimpleMovieDTO findSimpleById(Long id) {
        log.debug("finding movie by id {}: to movie simple dto", id);
        return movieMapper.toSimpleDTO(movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", id)));
    }

    @Override
    public MovieDTO findByName(String name) {
        log.debug("finding movie by name {}: to movie dto", name);
        return movieMapper.toDTO(movieRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", name)));
    }

    @Override
    public SimpleMovieDTO findSimpleByName(String name) {
        log.debug("finding movie by name {}: to movie simple dto", name);
        return movieMapper.toSimpleDTO(movieRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", name)));
    }

    @Override
    public List<MovieDTO> findAll() {
        log.debug("finding all movies: to movie dto");
        return movieRepository.findAll().stream().map(movieMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SimpleMovieDTO> findSimpleAll() {
        log.debug("finding all movies: to movie simple dto");
        return movieRepository.findAll().stream().map(movieMapper::toSimpleDTO)
                .collect(Collectors.toList());
    }
}

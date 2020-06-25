package com.cinema.point.service.impl;

import com.cinema.point.domain.Actor;
import com.cinema.point.domain.Movie;
import com.cinema.point.dto.MovieDTO;
import com.cinema.point.dto.SimpleMovieDTO;
import com.cinema.point.errors.ResourceNotFoundException;
import com.cinema.point.repository.ActorRepository;
import com.cinema.point.repository.MovieRepository;
import com.cinema.point.service.MovieService;
import com.cinema.point.service.mapper.MovieMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MovieServiceImpl implements MovieService {

    MovieRepository movieRepository;
    MovieMapper movieMapper;
    ActorRepository actorRepository;

    public MovieServiceImpl(MovieRepository movieRepository,
                            MovieMapper movieMapper,
                            ActorRepository actorRepository) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
        this.actorRepository = actorRepository;
    }

    @Override
    @Transactional
    public MovieDTO create(MovieDTO movieDTO) {
        log.debug("creating new movie {}", movieDTO);
        Movie movie = movieMapper.toEntity(movieDTO);
        Set<Actor> actors = movieDTO.getActorsIds().stream()
                .map(id -> actorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Actor", id)))
                .collect(Collectors.toSet());
        movie.setActors(actors);
        return movieMapper.toDTO(movieRepository.save(movie));
    }

    @Override
    public void deleteById(Long id) {
        log.debug("deleting movie by id {}", id);
        movieRepository.deleteById(id);
    }

    @Override
    public MovieDTO update(MovieDTO movieDTO) {
        log.debug("updating movie {}", movieDTO);
        Movie movie = movieMapper.toEntity(movieDTO);
        return movieMapper.toDTO(movieRepository.save(movie));
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

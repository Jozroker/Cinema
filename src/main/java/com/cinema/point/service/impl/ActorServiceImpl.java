package com.cinema.point.service.impl;

import com.cinema.point.domain.Actor;
import com.cinema.point.dto.ActorDTO;
import com.cinema.point.errors.ResourceNotFoundException;
import com.cinema.point.repository.ActorRepository;
import com.cinema.point.service.ActorService;
import com.cinema.point.service.mapper.ActorMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ActorServiceImpl implements ActorService {

    ActorRepository actorRepository;
    ActorMapper actorMapper;

    public ActorServiceImpl(ActorRepository actorRepository, ActorMapper actorMapper) {
        this.actorRepository = actorRepository;
        this.actorMapper = actorMapper;
    }

    @Override
    public ActorDTO create(ActorDTO actorDTO) {
        log.debug("creating new actor {}", actorDTO);
        Actor actor = actorMapper.toEntity(actorDTO);
        return actorMapper.toDTO(actorRepository.save(actor));
    }

    @Override
    public void deleteById(Long id) {
        log.debug("deleting actor by id {}", id);
        actorRepository.deleteById(id);
    }

    @Override
    public List<ActorDTO> findByMovieId(Long id) {
        log.debug("finding actors by movie id {}", id);
        List<Actor> actors = actorRepository.findByMovieId(id);
        return actors.stream()
                .map(actorMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public ActorDTO findById(Long id) {
        log.debug("finding actor by id {}", id);
        return actorRepository.findById(id).map(actorMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Actor", id));
    }

    @Override
    public List<ActorDTO> findAll() {
        log.debug("finding all actors");
        return actorRepository.findAll().stream()
                .map(actorMapper::toDTO).collect(Collectors.toList());
    }
}

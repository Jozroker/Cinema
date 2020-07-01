package com.cinema.point.service;

import com.cinema.point.dto.ActorDTO;

import java.util.List;

public interface ActorService {

    ActorDTO create(ActorDTO actorDTO);

    void deleteById(Long id);

    List<ActorDTO> findByMovieId(Long id);

    ActorDTO findById(Long id);

    List<ActorDTO> findAll();
}

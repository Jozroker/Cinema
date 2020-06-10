package com.cinema.point.service;

import com.cinema.point.dto.ActorDTO;

public interface ActorService {

    ActorDTO create(ActorDTO actorDTO);

    void deleteById(ActorDTO actorDTO);


}

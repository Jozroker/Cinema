package com.cinema.point.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
public class MovieCreationDTO {

    private Long id;

    @NotNull(message = "required field")
    private String name;

    @NotNull(message = "required field")
    @Max(value = 3000, message = "too long description")
    private String description;

    private Long duration;

    private byte[] picture;

    private Set<ActorDTO> actors = new HashSet<>();
}

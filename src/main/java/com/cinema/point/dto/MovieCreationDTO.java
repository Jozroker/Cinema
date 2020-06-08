package com.cinema.point.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
public class MovieCreationDTO {

    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    @Max(value = 3000, message = "too long description")
    private String description;

    @NotNull
    private Long duration;

    @NotNull
    private byte[] picture;

    private Set<ActorDTO> actors = new HashSet<>();
}

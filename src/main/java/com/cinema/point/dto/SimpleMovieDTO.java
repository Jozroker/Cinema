package com.cinema.point.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class SimpleMovieDTO {

    private Long id;

    @NotEmpty(message = "name is required")
    private String name;

    @NotEmpty(message = "picture is required")
    private byte[] picture;
}

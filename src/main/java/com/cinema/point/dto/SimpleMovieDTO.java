package com.cinema.point.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class SimpleMovieDTO {

    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private byte[] picture;
}

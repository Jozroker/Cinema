package com.cinema.point.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MovieDTO {

    private Long id;

    @NotNull(message = "required field")
    private String name;
}

package com.cinema.point.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class MovieDTO {

    private Long id;

    @NotEmpty
    private String name;
}

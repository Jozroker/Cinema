package com.cinema.point.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ActorDTO {

    private Long id;

    @NotEmpty(message = "first name value is required")
    private String firstName;

    @NotEmpty(message = "last name value is required")
    private String lastName;

    private byte[] profilePicture;

    private String pictureString;
}

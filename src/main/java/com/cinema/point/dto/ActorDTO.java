package com.cinema.point.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ActorDTO {

    private Long id;

    @NotEmpty(message = "{state.required.field}")
    private String firstName;

    @NotEmpty(message = "{state.required.field}")
    private String lastName;

    private byte[] profilePicture;

    private String pictureString;
}

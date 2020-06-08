package com.cinema.point.dto;

import javax.validation.constraints.NotEmpty;

public class ActorDTO {

    private Long id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    private byte[] profilePicture;
}

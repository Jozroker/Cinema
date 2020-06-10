package com.cinema.point.dto;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.validation.constraints.NotEmpty;

@Data
public class ActorDTO {

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] profilePicture;
}

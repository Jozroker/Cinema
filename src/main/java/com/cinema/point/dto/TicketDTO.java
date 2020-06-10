package com.cinema.point.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
public class TicketDTO {

    private Long id;

    @NotEmpty
    private int row;

    @NotEmpty
    private int column;

    @NotNull(message = "required field")
    private Date seanceDate;

//    private SeanceDTO seance;
private Long seanceId;
}

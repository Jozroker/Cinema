package com.cinema.point.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
public class TicketDTO {

    private Long id;

    @NotEmpty(message = "row isn't selected")
    private int row;

    @NotEmpty(message = "column isn't selected")
    private int column;

    @NotNull(message = "required field")
    private Date seanceDate;

//    private SeanceDTO seance;
private Long seanceId;
}

package com.cinema.point.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
public class TicketDTO {

    private Long id;

    @NotNull(message = "required field")
    private int row;

    @NotNull(message = "required field")
    private int column;

    @NotNull(message = "required field")
    private Date seanceDate;

    private SeanceDTO seance;
}

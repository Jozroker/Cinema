package com.cinema.point.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class TicketDTO {

    private Long id;

    private int row;

    private int column;

    private Date seanceDate;

    private Long seanceId;
}

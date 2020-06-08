package com.cinema.point.dto;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.sql.Time;

@Data
public class SeanceDTO {

    private Long id;

    @NotEmpty
    private Time movieBeginTime;

    @NotEmpty
    @Digits(integer = 6, fraction = 2)
    private BigDecimal ticketPrice;

    @NotEmpty
    private Long hallId;

    private MovieDTO movie;
}

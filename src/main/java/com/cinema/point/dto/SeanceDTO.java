package com.cinema.point.dto;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Time;

@Data
public class SeanceDTO {

    private Long id;

    @NotNull(message = "required field")
    private Time movieBeginTime;

    @NotNull(message = "required field")
    @Digits(integer = 6, fraction = 2)
    private BigDecimal ticketPrice;

    @NotNull(message = "required field")
    private Long hallId;

    private MovieDTO movie;
}

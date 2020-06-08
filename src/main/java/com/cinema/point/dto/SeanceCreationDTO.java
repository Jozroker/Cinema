package com.cinema.point.dto;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

@Data
public class SeanceCreationDTO {

    private Long id;

    @NotNull(message = "required field")
    @FutureOrPresent
    private Date seanceDateFrom;

    @NotNull(message = "required field")
    @Future
    private Date seanceDateTo;

    @NotNull(message = "required field")
    private Time movieBeginTime;

    @NotNull(message = "required field")
    @Digits(integer = 6, fraction = 2)
    private BigDecimal ticketPrice;

    @NotNull(message = "required field")
    private Long hallId;

    private MovieDTO movie;


}

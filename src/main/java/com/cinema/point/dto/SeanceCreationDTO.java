package com.cinema.point.dto;

import lombok.Data;

import javax.validation.constraints.*;
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

    @NotEmpty
    @Digits(integer = 6, fraction = 2)
    private BigDecimal ticketPrice;

    @NotNull
//    private Hall hall;
    private Long hallId;

    //    private MovieDTO movie;
    private Long movieId;


}

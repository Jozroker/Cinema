package com.cinema.point.dto;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Data
public class SeanceDTO {

    private Long id;

    @NotEmpty(message = "begin time value is required")
    private Time movieBeginTime;

    @NotEmpty(message = "ticket price is required")
    @Digits(integer = 6, fraction = 2)
    private BigDecimal ticketPrice;

    @NotEmpty(message = "hall isn't selected")
    private Long hallId;

    //    private MovieDTO movie;
    private Long movieId;

    public String timeToString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.US);
        LocalTime time = movieBeginTime.toLocalTime();
        return formatter.format(time);
    }
}

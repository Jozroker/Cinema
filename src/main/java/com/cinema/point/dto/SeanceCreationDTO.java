package com.cinema.point.dto;

import com.cinema.point.domain.Day;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Data
public class SeanceCreationDTO {

    private Long id;

    @NotNull(message = "required field")
    @FutureOrPresent
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date seanceDateFrom;

    @NotNull(message = "required field")
    @Future
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date seanceDateTo;

    @NotNull(message = "required field")
    private Time movieBeginTime;

    private Time movieEndTime;

    @NotEmpty(message = "ticket price value is required")
    @Digits(integer = 6, fraction = 2)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal ticketPrice;

    @NotNull(message = "hall isn't selected")
//    private Hall hall;
    private Long hallId;

    //    private MovieDTO movie;
    private Long movieId;

    private Set<Day> day = new HashSet<>();

    public String timeToString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.US);
        LocalTime time = movieBeginTime.toLocalTime();
        return formatter.format(time);
    }
}

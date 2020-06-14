package com.cinema.point.contoller;

import com.cinema.point.domain.Day;
import com.cinema.point.dto.MovieDTO;
import com.cinema.point.dto.SeanceDTO;
import com.cinema.point.dto.UserDTO;
import com.cinema.point.service.MovieService;
import com.cinema.point.service.SeanceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class InfoController {

    SeanceService seanceService;

    MovieService movieService;

    public InfoController(SeanceService seanceService, MovieService movieService) {
        this.seanceService = seanceService;
        this.movieService = movieService;
    }

    @GetMapping({"/home", "/"})
    public String home(Model model, @ModelAttribute("user") UserDTO user) {
        Date dateNow = Date.valueOf(LocalDate.now());
        Instant dateTimeNow = Instant.now();
//        List<MovieDTO> movies =
//                seanceService.findByDateBetween(Date.valueOf(dateTimeNow.atOffset(ZoneOffset.UTC).toLocalDate())).stream()
//                .map(seanceDTO -> movieService.findById(seanceDTO.getMovieId()))
//                .collect(Collectors.toList());
        List<String> movies = seanceService.findByDateBetween(Date.valueOf(dateTimeNow.atOffset(ZoneOffset.UTC).toLocalDate())).stream()
                .map(seanceDTO -> {
                    MovieDTO movieDTO =
                            movieService.findById(seanceDTO.getMovieId());
                    byte[] encodeBase64 = Base64.encodeBase64(movieDTO.getPicture());
                    return new String(encodeBase64, StandardCharsets.UTF_8);

                })
                .collect(Collectors.toList());
        List<List<SeanceDTO>> schedule = new ArrayList<>();
        List<Day> days = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            days.add(Day.valueOf(String.valueOf(dateTimeNow.atOffset(ZoneOffset.UTC).getDayOfWeek())));
            schedule.add(seanceService.findByDateBetween(Date.valueOf(dateTimeNow.atOffset(ZoneOffset.UTC).toLocalDate())));
            dateTimeNow = dateTimeNow.plus(1, ChronoUnit.DAYS);
        }
        model.addAttribute("days", days);
        model.addAttribute("schedule", schedule);
        model.addAttribute("movies", movies);
        return "home";
    }
}

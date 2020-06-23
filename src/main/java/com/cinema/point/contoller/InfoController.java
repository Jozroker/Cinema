package com.cinema.point.contoller;

import com.cinema.point.domain.Day;
import com.cinema.point.dto.SeanceCreationDTO;
import com.cinema.point.dto.SimpleMovieDTO;
import com.cinema.point.repository.SeanceRepository;
import com.cinema.point.service.MovieService;
import com.cinema.point.service.SeanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class InfoController {

    SeanceService seanceService;

    SeanceRepository seanceRepository;

    MovieService movieService;

    public InfoController(SeanceService seanceService, SeanceRepository seanceRepository, MovieService movieService) {
        this.seanceService = seanceService;
        this.seanceRepository = seanceRepository;
        this.movieService = movieService;
    }

    @GetMapping({"/home", "/"})
    //todo add site icon
    public String home(Model model) {
        Date dateNow = Date.valueOf(LocalDate.now());
        List<SimpleMovieDTO> currentMovies =
                seanceService.findByDateBetween(dateNow).stream()
                        .map(seanceDTO -> movieService.findSimpleById(seanceDTO.getMovieId())).distinct()
                        .collect(Collectors.toList());
        List<Day> days = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            days.add(Day.valueOf(String.valueOf(dateNow.toLocalDate().getDayOfWeek())));
            dateNow = Date.valueOf(dateNow.toLocalDate().plus(1, ChronoUnit.DAYS));
        }
        model.addAttribute("days", days);
        model.addAttribute("movies", currentMovies);
        return "home";
    }

    private void cleanData(Date date) {
        Date tomorrow = Date.valueOf(date.toLocalDate().minus(1, ChronoUnit.DAYS));
        List<SeanceCreationDTO> deleting = seanceService.findBySeanceDateTo(tomorrow);
        Iterator<SeanceCreationDTO> iter = deleting.iterator();
        while (iter.hasNext()) {
            seanceService.deleteById(iter.next().getId());
        }
    }
}

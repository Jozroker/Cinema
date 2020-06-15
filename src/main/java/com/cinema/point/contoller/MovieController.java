package com.cinema.point.contoller;

import com.cinema.point.service.MovieService;
import com.cinema.point.service.SeanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class MovieController {

    MovieService movieService;

    SeanceService seanceService;

    public MovieController(MovieService movieService, SeanceService seanceService) {
        this.movieService = movieService;
        this.seanceService = seanceService;
    }


}

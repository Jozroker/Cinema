package com.cinema.point.contoller;

import com.cinema.point.domain.Day;
import com.cinema.point.domain.Hall;
import com.cinema.point.dto.*;
import com.cinema.point.errors.ResourceNotFoundException;
import com.cinema.point.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class MovieController {

    private final MovieService movieService;

    private final SeanceService seanceService;

    private final ActorService actorService;

    private final TicketService ticketService;

    private final HallService hallService;

    public MovieController(MovieService movieService,
                           SeanceService seanceService,
                           ActorService actorService,
                           TicketService ticketService,
                           HallService hallService) {
        this.movieService = movieService;
        this.seanceService = seanceService;
        this.actorService = actorService;
        this.ticketService = ticketService;
        this.hallService = hallService;
    }

    @GetMapping("/movies")
    public String movies(Model model) {
        List<SimpleMovieDTO> movies = movieService.findSimpleAll();
        model.addAttribute("movies", movies);
        return "movies";
    }

    @GetMapping("/movie/{id}")
    public String movie(Model model, @PathVariable Long id) {
        MovieDTO movie = movieService.findById(id);
        Date dateNow = Date.valueOf(LocalDate.now());
        List<Day> days = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            days.add(Day.valueOf(String.valueOf(dateNow.toLocalDate().getDayOfWeek())));
            dateNow = Date.valueOf(dateNow.toLocalDate().plus(1, ChronoUnit.DAYS));
        }
        List<ActorDTO> actors = actorService.findByMovieId(movie.getId());
        model.addAttribute("days", days);
        model.addAttribute("actors", actors);
        model.addAttribute("movie", movie);
        return "movie";
    }

    @GetMapping("/movies/list")
    @ResponseBody
    public Map<Long, String> getAllMovies() {
        Map<Long, String> values = new HashMap<>();
        movieService.findSimpleAll().forEach(movie -> values.put(movie.getId(), movie.getName()));
        return values;
    }

    @GetMapping("/admin/create/movie")
    public String createMovie() {
        return "create_movie";
    }

    @GetMapping("/admin/change/movie/{movieId}")
    public String changeMovie(Model model, @PathVariable Long movieId) {
        MovieDTO movie = movieService.findById(movieId);
        List<ActorDTO> actors = actorService.findByMovieId(movieId);
        model.addAttribute("movie", movie);
        model.addAttribute("actors", actors);
        return "change_movie";
    }

    @GetMapping("/admin/create/actor")
    public String createActor(Model model) {
        ActorDTO actor = new ActorDTO();
        model.addAttribute("actor", actor);
        return "create_actor";
    }

    @GetMapping("/admin/get/actors")
    @ResponseBody
    public List<ActorDTO> getActors() {
        //todo create name comparator
        return actorService.findAll();
    }

    @PostMapping("/admin/create/actor")
    public String createActor(@Valid @ModelAttribute("actor") ActorDTO actor,
                              BindingResult bindingResult,
                              @RequestParam("file") MultipartFile file) throws IOException {
        log.info("register new actor {}", actor);
        if (bindingResult.hasErrors()) {
            return "create_actor";
        }
        if (file.isEmpty()) {
            //todo set relative path
            //todo move this to service level
            //todo check actor is already created
            File imagePath = new File("F:\\PC_Educate\\Programming\\java" +
                    "\\cinema" +
                    "\\src\\main\\webapp\\resources\\image\\default-avatar" +
                    ".jpg");
            BufferedImage image = ImageIO.read(imagePath);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", bos);
            byte[] data = bos.toByteArray();
            actor.setProfilePicture(data);
        } else {
            actor.setProfilePicture(file.getBytes());
        }
        actorService.create(actor);
        return "create_actor";
    }

    @PostMapping(value = "/admin/create/movie", consumes =
            {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public String createMovie(@RequestBody String movie) throws JsonProcessingException {
        MovieDTO movieDTO = new ObjectMapper().readValue(movie, MovieDTO.class);
        try {
            movieService.findByName(movieDTO.getName());
        } catch (ResourceNotFoundException e) {
            movieService.save(movieDTO);
        }
        return "exists";
    }

    @PostMapping(value = "/admin/change/movie", consumes =
            {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public void changeMovie(@RequestBody String movie) throws JsonProcessingException {
        MovieDTO movieDTO = new ObjectMapper().readValue(movie, MovieDTO.class);
        movieService.save(movieDTO);
    }

    @PostMapping("/admin/delete/movie")
    public void deleteMovie(@RequestParam Long movieId) {
        movieService.deleteById(movieId);
    }

    @GetMapping("/movie/hall/reserved")
    @ResponseBody
    public Map<Integer, List<Integer>> getReservedPlaces(@RequestParam String date,
                                                         @RequestParam String time,
                                                         @RequestParam String movieId) {
        Map<Integer, List<Integer>> reservedPlaces = new HashMap<>();
        SeanceDTO seance = seanceService.findByDateBetween(Date.valueOf(date)).stream()
                .filter(s -> s.getMovieBeginTime().equals(Time.valueOf(time + ":00")))
                .filter(s -> s.getMovieId().equals(Long.parseLong(movieId)))
                .findFirst().orElseThrow(() -> new ResourceNotFoundException("Seance", ""));
        List<TicketDTO> tickets =
                ticketService.findBySeanceId(seance.getId()).stream()
                        .filter(ticket -> ticket.getSeanceDate().equals(Date.valueOf(date)))
                        .collect(Collectors.toList());
        tickets.forEach(t -> {
            int row = t.getRow();
            int column = t.getColumn();
            if (!reservedPlaces.containsKey(row)) {
                reservedPlaces.put(row, new ArrayList<>());
            }
            reservedPlaces.get(row).add(column);
        });
        return reservedPlaces;
    }

    @GetMapping("/movie/hall")
    @ResponseBody
    public Hall getHallByMovie(@RequestParam String date,
                               @RequestParam String time,
                               @RequestParam String movieId) {
        SeanceDTO seance = seanceService.findByDateBetween(Date.valueOf(date)).stream()
                .filter(s -> s.getMovieBeginTime().equals(Time.valueOf(LocalTime.parse(time))))
                .filter(s -> s.getMovieId().equals(Long.parseLong(movieId)))
                .findFirst().orElseThrow(() -> new ResourceNotFoundException("Seance", ""));
        return hallService.findById(seance.getHallId());
    }
}

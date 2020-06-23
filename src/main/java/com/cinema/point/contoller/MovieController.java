package com.cinema.point.contoller;

import com.cinema.point.domain.Day;
import com.cinema.point.domain.Seance;
import com.cinema.point.dto.ActorDTO;
import com.cinema.point.dto.MovieDTO;
import com.cinema.point.dto.SeanceCreationDTO;
import com.cinema.point.dto.SimpleMovieDTO;
import com.cinema.point.errors.ResourceNotFoundException;
import com.cinema.point.repository.SeanceRepository;
import com.cinema.point.service.ActorService;
import com.cinema.point.service.MovieService;
import com.cinema.point.service.SeanceService;
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
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class MovieController {

    MovieService movieService;

    SeanceService seanceService;

    SeanceRepository seanceRepository;

    ActorService actorService;

    public MovieController(MovieService movieService, SeanceService seanceService, SeanceRepository seanceRepository, ActorService actorService) {
        this.movieService = movieService;
        this.seanceService = seanceService;
        this.seanceRepository = seanceRepository;
        this.actorService = actorService;
    }

    @GetMapping("/movies")
    public String movies(Model model) {
        List<SimpleMovieDTO> movies = movieService.findSimpleAll();
        model.addAttribute("movies", movies);
        return "movies";
    }

    @GetMapping("/movie/{id}")
    public String movie(Model model, @PathVariable Long id, @RequestParam(defaultValue = "") String date) {
        MovieDTO movie = movieService.findById(id);
        Date dateNow = Date.valueOf(LocalDate.now());
//        cleanData(dateNow);
        List<Seance> schedule;
        if (date.equals("")) {
            schedule =
                    seanceRepository.findByDateBetween(dateNow).stream()
                            .filter(seance -> seance.getMovie().getId().equals(id))
                            .collect(Collectors.toList());
        } else {
            Date param;
            try {
                param = Date.valueOf(date);
            } catch (Exception e) {
                return "redirect:/home";
            }
            schedule =
                    seanceRepository.findByDateBetween(param).stream()
                            .filter(seance -> seance.getMovie().getId().equals(id))
                            .collect(Collectors.toList());
        }
        List<Day> days = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            days.add(Day.valueOf(String.valueOf(dateNow.toLocalDate().getDayOfWeek())));
            dateNow = Date.valueOf(dateNow.toLocalDate().plus(1, ChronoUnit.DAYS));
        }
        List<ActorDTO> actors = actorService.findByMovieId(movie.getId());
        model.addAttribute("days", days);
        model.addAttribute("actors", actors);
        model.addAttribute("schedule", schedule);
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

    @GetMapping("/admin/create/actor")
    public String createActor(Model model) {
        ActorDTO actor = new ActorDTO();
        model.addAttribute("actor", actor);
        return "create_actor";
    }

    @GetMapping("/admin/get/actors")
    @ResponseBody
    public List<ActorDTO> getActors() {
        return actorService.findAll();
    }

    @PostMapping("/admin/create/actor")
    public String createActor(@Valid @ModelAttribute("actor") ActorDTO actor,
                              BindingResult bindingResult, @RequestParam("file") MultipartFile file) throws IOException {
        if (bindingResult.hasErrors()) {
            return "create_actor";
        }
        log.info("register new actor {}", actor);
        if (file.isEmpty()) {
            File imagePath = new File("F:\\PC_Educate\\Programming\\java" +
                    "\\cinema" +
                    "\\src\\main\\webapp\\resources\\image\\default-avatar.png");
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
            movieService.create(movieDTO);
            return "not exists";
        }
        return "exists";
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

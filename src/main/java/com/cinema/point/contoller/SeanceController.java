package com.cinema.point.contoller;

import com.cinema.point.domain.Hall;
import com.cinema.point.dto.MovieDTO;
import com.cinema.point.dto.SeanceCreationDTO;
import com.cinema.point.dto.SeanceDTO;
import com.cinema.point.dto.TicketDTO;
import com.cinema.point.errors.ResourceNotFoundException;
import com.cinema.point.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class SeanceController {

    TicketService ticketService;

    MovieService movieService;

    SeanceService seanceService;

    HallService hallService;

    UserService userService;

    public SeanceController(TicketService ticketService, MovieService movieService, SeanceService seanceService, HallService hallService, UserService userService) {
        this.ticketService = ticketService;
        this.movieService = movieService;
        this.seanceService = seanceService;
        this.hallService = hallService;
        this.userService = userService;
    }

    @GetMapping("/seance/order")
    public String orderBySeance(Model model, @RequestParam String seanceId) {
        SeanceDTO seance = seanceService.findById(Long.parseLong(seanceId));
        MovieDTO movie = movieService.findById(seance.getMovieId());
        Map<String, List<Integer>> validDates = findValidDates(movie.getId());
        model.addAttribute("movie", movie);
        model.addAttribute("dates", validDates);
        return "ticket_order";
    }

    @GetMapping("/movie/order")
    public String orderByMovie(Model model, @RequestParam String movieId) {
        MovieDTO movie = movieService.findById(Long.parseLong(movieId));
        Map<String, List<Integer>> validDates = findValidDates(movie.getId());
        model.addAttribute("movie", movie);
        model.addAttribute("dates", validDates);
        return "ticket_order";
    }

    @GetMapping("order/times")
    @ResponseBody
    public Map<Integer, List<Integer>> getValidTimes(@RequestParam String movieId,
                                                     @RequestParam String date) {
        Map<Integer, List<Integer>> times = new HashMap<>();
        seanceService.findByDateBetween(Date.valueOf(date)).stream()
                .filter(s -> s.getMovieId() == Long.valueOf(movieId))
                .forEach(s -> {
                    int hour =
                            s.getMovieBeginTime().toLocalTime().getHour();
                    int minute =
                            s.getMovieBeginTime().toLocalTime().getMinute();
                    if (!times.containsKey(hour)) {
                        times.put(hour, new ArrayList<>());
                    }
                    times.get(hour).add(minute);
                });
        return times;
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

    @GetMapping("/seance")
    @ResponseBody
    public SeanceDTO getSeance(@RequestParam String date,
                               @RequestParam String time,
                               @RequestParam String movieId) {
        return seanceService.findByDateBetween(Date.valueOf(date)).stream()
                .filter(s -> s.getMovieBeginTime().equals(Time.valueOf(LocalTime.parse(time))))
                .filter(s -> s.getMovieId().equals(Long.parseLong(movieId)))
                .findFirst().orElseThrow(() -> new ResourceNotFoundException("Seance", ""));
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
                        .filter(ticket -> ticket.getSeanceDate().equals(Date.valueOf(date))).collect(Collectors.toList());
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

    @PostMapping("/order/user/ticket")
//    @Transactional
    public void orderByUser(@RequestParam String row,
                            @RequestParam String column,
                            @RequestParam String seanceId,
                            @RequestParam String date,
                            Principal principal) {
        TicketDTO ticket = new TicketDTO();
        ticket.setRow(Integer.parseInt(row));
        ticket.setColumn(Integer.parseInt(column));
        ticket.setSeanceId(Long.parseLong(seanceId));
        ticket.setSeanceDate(Date.valueOf(date));
//        ticket.setSeanceTime(Time.valueOf(time + ":00"));
        Long userId = userService.findByUsername(principal.getName()).getId();
        ticketService.addTicketToUser(ticket, userId);
//        return "redirect:/cabinet#tickets";
    }

    @PostMapping("/order/ticket")
    public void order(@RequestParam String row,
                      @RequestParam String column,
                      @RequestParam String seanceId,
                      @RequestParam String date) {
        TicketDTO ticket = new TicketDTO();
        ticket.setRow(Integer.parseInt(row));
        ticket.setColumn(Integer.parseInt(column));
        ticket.setSeanceId(Long.parseLong(seanceId));
        ticket.setSeanceDate(Date.valueOf(date));
        ticketService.create(ticket);
//        return "redirect:/cabinet#tickets";
    }

    private Map<String, List<Integer>> findValidDates(Long movieId) {
        List<SeanceCreationDTO> validSeances = seanceService.findCreationByMovieId(movieId);
        Map<String, List<Integer>> validDates = new HashMap<>();
        validSeances.forEach(s -> {
            Date date = s.getSeanceDateFrom();
            String month;
            int day;
            while (date.before(s.getSeanceDateTo())) {
                month = date.toLocalDate().getMonth().toString().toLowerCase();
                day = date.toLocalDate().getDayOfMonth();
                if (!(validDates.containsKey(month) && validDates.get(month).contains(day))) {
                    if (!validDates.containsKey(month)) {
                        validDates.put(month, new ArrayList<>());
                    }
                    validDates.get(month).add(day);
//                                    dates.put(month, day);
                }
                date = Date.valueOf(date.toLocalDate().plus(1,
                        ChronoUnit.DAYS));
            }
            month = s.getSeanceDateTo().toLocalDate().getMonth().toString().toLowerCase();
            day = s.getSeanceDateTo().toLocalDate().getDayOfMonth();
            if (!(validDates.containsKey(month) && validDates.get(month).contains(day))) {
                if (!validDates.containsKey(month)) {
                    validDates.put(month, new ArrayList<>());
                }
                validDates.get(month).add(day);
            }
        });
        return validDates;
    }


}

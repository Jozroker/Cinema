package com.cinema.point.contoller;

import com.cinema.point.domain.Day;
import com.cinema.point.domain.Seance;
import com.cinema.point.domain.comparator.SeanceTimeComparator;
import com.cinema.point.dto.MovieDTO;
import com.cinema.point.dto.SeanceCreationDTO;
import com.cinema.point.dto.SeanceDTO;
import com.cinema.point.dto.TicketDTO;
import com.cinema.point.errors.ResourceNotFoundException;
import com.cinema.point.repository.SeanceRepository;
import com.cinema.point.service.MovieService;
import com.cinema.point.service.SeanceService;
import com.cinema.point.service.TicketService;
import com.cinema.point.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class SeanceController {

    //todo change all RequestParam to JSON body

    private final TicketService ticketService;

    private final MovieService movieService;

    private final SeanceService seanceService;

    private final SeanceRepository seanceRepository;

    private final UserService userService;

    public SeanceController(TicketService ticketService,
                            MovieService movieService,
                            SeanceService seanceService,
                            SeanceRepository seanceRepository,
                            UserService userService) {
        this.ticketService = ticketService;
        this.movieService = movieService;
        this.seanceService = seanceService;
        this.seanceRepository = seanceRepository;
        this.userService = userService;
    }

    @GetMapping("/seance/order/{seanceId}/{date}")
    public String orderBySeance(Model model, @PathVariable String seanceId,
                                @PathVariable String date) {
        SeanceDTO seance = seanceService.findById(Long.parseLong(seanceId));
        MovieDTO movie = movieService.findById(seance.getMovieId());
        Map<String, List<Integer>> validDates = findValidDates(movie.getId());
        model.addAttribute("movie", movie);
        model.addAttribute("dates", validDates);
        model.addAttribute("seanceMovieTime", seance.getMovieBeginTime().toString());
        model.addAttribute("seanceDate", date);
        return "ticket_order";
    }

    @GetMapping("/movie/order/{movieId}")
    public String orderByMovie(Model model, @PathVariable String movieId) {
        MovieDTO movie = movieService.findById(Long.parseLong(movieId));
        Map<String, List<Integer>> validDates = findValidDates(movie.getId());
        model.addAttribute("movie", movie);
        model.addAttribute("dates", validDates);
        return "ticket_order";
    }

    @GetMapping("/order/times")
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

    @PostMapping("/order/user/ticket")
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
        Long userId = userService.findByUsername(principal.getName()).getId();
        ticketService.addTicketToUser(ticket, userId);
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
    }

    @GetMapping("/schedule")
    public String schedule() {
        return "schedule";
    }

    @GetMapping("/schedule/seances")
    @ResponseBody
    public List<Seance> getSeancesByDate(@RequestParam String date) {
        Day currentDay =
                Day.valueOf(Date.valueOf(date).toLocalDate().getDayOfWeek().toString());
        return seanceRepository.findByDateBetween(Date.valueOf(date)).stream()
                .filter(seance -> seance.getDay().contains(currentDay))
                .sorted(new SeanceTimeComparator()).collect(Collectors.toList());
    }

    @GetMapping("/movie/{id}/seances")
    @ResponseBody
    public List<Seance> getSeancesByMovie(@PathVariable Long id,
                                          @RequestParam String date) {
        Day currentDay =
                Day.valueOf(Date.valueOf(date).toLocalDate().getDayOfWeek().toString());
        return seanceRepository.findByDateBetween(Date.valueOf(date)).stream()
                .filter(seance -> seance.getMovie().getId().equals(id))
                .filter(seance -> seance.getDay().contains(currentDay))
                .sorted(new SeanceTimeComparator())
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/admin/creation/seance", consumes =
            MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String changeSeance(@RequestBody SeanceCreationDTO seance,
                               @RequestParam String movieName) {
        try {
            Long movieId = movieService.findByName(movieName).getId();
            seance.setMovieId(movieId);
        } catch (ResourceNotFoundException e) {
            return "fail movie";
        }
        MovieDTO movie = movieService.findById(seance.getMovieId());
        seance.setMovieEndTime(Time.valueOf(seance.getMovieBeginTime().toLocalTime()
                .plus(movie.getDuration(), ChronoUnit.MILLIS)));
        if (findSameSeances(seance)) {
            seanceService.save(seance);
            return "not exists";
        }
        return "exists";
    }

    @PostMapping("/admin/delete/seance")
    public String deleteSeance(@RequestParam Long seanceId) {
        seanceService.deleteById(seanceId);
        return "schedule";
    }

    //todo move this to service level

    private Map<String, List<Integer>> findValidDates(Long movieId) {
        List<SeanceCreationDTO> validSeances = seanceService.findCreationByMovieId(movieId);
        Map<String, List<Integer>> validDates = new HashMap<>();
        validSeances.forEach(s -> {
            Date date = s.getSeanceDateFrom();
            Date previousDate = Date.valueOf(LocalDate.now().minus(1,
                    ChronoUnit.DAYS));
            String month;
            int day;
            while (date.before(s.getSeanceDateTo())) {
                if (date.after(previousDate)) {
                    month = date.toLocalDate().getMonth().toString().toLowerCase();
                    day = date.toLocalDate().getDayOfMonth();
                    if (!(validDates.containsKey(month) && validDates.get(month).contains(day))) {
                        if (!validDates.containsKey(month)) {
                            validDates.put(month, new ArrayList<>());
                        }
                        validDates.get(month).add(day);
                    }
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

    private boolean compareTime(Time currentMovieBeginTime,
                                Time currentMovieEndTime,
                                Time newMovieBeginTime,
                                Time newMovieEndTime) {
        SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
        java.util.Date currentMovieBegin = null;
        java.util.Date currentMovieEnd = null;
        java.util.Date newMovieBegin = null;
        java.util.Date newMovieEnd = null;
        try {
            currentMovieBegin = parser.parse(currentMovieBeginTime.toString());
            currentMovieEnd = parser.parse(currentMovieEndTime.toString());
            newMovieBegin = parser.parse(newMovieBeginTime.toString());
            newMovieEnd = parser.parse(newMovieEndTime.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (((newMovieBegin.after(currentMovieBegin) || newMovieBegin.equals(currentMovieBegin)) &&
                (newMovieBegin.before(currentMovieEnd) || newMovieBegin.equals(currentMovieEnd))) ||
                ((newMovieEnd.after(currentMovieBegin) || newMovieEnd.equals(currentMovieBegin)) &&
                        (newMovieEnd.before(currentMovieEnd) || newMovieEnd.equals(currentMovieEnd))));
    }

    private boolean findSameSeances(SeanceCreationDTO seance) {
        List<SeanceCreationDTO> sameSeances = seanceService
                .findBySeanceDates(seance.getSeanceDateFrom(),
                        seance.getSeanceDateTo())
                .stream().filter(s -> !Collections.disjoint(s.getDay(),
                        seance.getDay()))
                .filter(s -> !s.getId().equals(seance.getId()))
                .filter(s -> s.getHallId().equals(seance.getHallId()))
                .filter(s -> compareTime(s.getMovieBeginTime(),
                        s.getMovieEndTime(),
                        seance.getMovieBeginTime(),
                        seance.getMovieEndTime())).collect(Collectors.toList());
        return sameSeances.isEmpty();
    }
}

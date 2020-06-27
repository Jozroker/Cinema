package com.cinema.point.contoller;

import com.cinema.point.contoller.validator.UserValidator;
import com.cinema.point.domain.Hall;
import com.cinema.point.dto.*;
import com.cinema.point.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class UserController {

    private final UserService userService;

    private final UserValidator userValidator;

    private final TicketService ticketService;

    private final MovieService movieService;

    private final SeanceService seanceService;

    private final HallService hallService;

    public UserController(UserService userService,
                          UserValidator userValidator,
                          TicketService ticketService,
                          MovieService movieService,
                          SeanceService seanceService,
                          HallService hallService) {
        this.userService = userService;
        this.userValidator = userValidator;
        this.ticketService = ticketService;
        this.movieService = movieService;
        this.seanceService = seanceService;
        this.hallService = hallService;
    }

    @InitBinder("registerUser")
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.addValidators(userValidator);
    }

    //todo return error fields
    @GetMapping("/authorization")
    public String get(Model model) {
        LoginUserDTO loginUserDTO = new LoginUserDTO();
        RegisterUserDTO registerUserDTO = new RegisterUserDTO();
        model.addAttribute("loginUser", loginUserDTO);
        model.addAttribute("registerUser", registerUserDTO);
        return "authorization";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("registerUser") RegisterUserDTO userDTO,
                           BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("loginUser", new LoginUserDTO());
            return "authorization";
        }
        log.info("register new user {}", userDTO);
        userService.save(userDTO);
        return "redirect:/authorization";
    }

    @GetMapping("/cabinet")
    public String cabinet(Model model, Principal principal) {
        UserDTO user = userService.findByUsername(principal.getName());
        List<TicketDTO> tickets = ticketService.findByUserId(user.getId());
        List<SeanceDTO> seances =
                tickets.stream().map(t -> seanceService.findByTicketId(t.getId()))
                        .collect(Collectors.toList());
        List<SimpleMovieDTO> movies =
                seances.stream().map(s -> movieService.findSimpleById(s.getMovieId()))
                        .collect(Collectors.toList());
        model.addAttribute("user", user);
        model.addAttribute("tickets", tickets);
        model.addAttribute("seances", seances);
        model.addAttribute("movies", movies);
        return "cabinet";
    }

    @GetMapping("/ticket")
    public String ticket(Model model, @RequestParam Long id) {
        TicketDTO ticket = ticketService.findById(id);
        SeanceDTO seance =
                seanceService.findByTicketId(id);
        SimpleMovieDTO movie = movieService.findSimpleById(seance.getMovieId());
        Hall hall = hallService.findById(seance.getHallId());
        model.addAttribute("ticket", ticket);
        model.addAttribute("seance", seance);
        model.addAttribute("movie", movie);
        model.addAttribute("hall", hall);
        return "ticket";
    }

}

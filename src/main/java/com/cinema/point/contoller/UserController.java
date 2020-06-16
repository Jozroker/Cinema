package com.cinema.point.contoller;

import com.cinema.point.contoller.validator.UserValidator;
import com.cinema.point.dto.LoginUserDTO;
import com.cinema.point.dto.RegisterUserDTO;
import com.cinema.point.dto.UserDTO;
import com.cinema.point.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;


@Controller
@Slf4j
public class UserController {

    UserService userService;

    UserValidator userValidator;

    public UserController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @InitBinder("registerUser")
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.addValidators(userValidator);
    }

    @GetMapping("/authorization")
    public String get(Model model) {
        LoginUserDTO loginUserDTO = new LoginUserDTO();
        RegisterUserDTO registerUserDTO = new RegisterUserDTO();
        model.addAttribute("loginUser", loginUserDTO);
        model.addAttribute("registerUser", registerUserDTO);
        return "authorization";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("registerUser") RegisterUserDTO userDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("loginUser", new LoginUserDTO());
            return "authorization";
        }
        log.info("register new user {}", userDTO);
        userService.create(userDTO);
        return "redirect:/authorization";
    }

    @GetMapping("/cabinet")
    public String cabinet(Model model, Principal principal) {
        UserDTO user = userService.findByUsername(principal.getName());
//        String picture = new String(user.getPicture(), StandardCharsets.UTF_8);
//        model.addAttribute("picture", picture);
        model.addAttribute("user", user);
        return "cabinet";
    }

}

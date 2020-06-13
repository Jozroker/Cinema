package com.cinema.point.contoller;

import com.cinema.point.dto.LoginUserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@Slf4j
public class UserController {

    @GetMapping("/login")
    public String loginGet(Model model) {
        LoginUserDTO userDTO = new LoginUserDTO();
        model.addAttribute("user", userDTO);
        return "login";
    }
}

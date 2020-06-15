package com.cinema.point.errors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ApplicationExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public String handle(ResourceNotFoundException exception, Model model) {
        model.addAttribute("message", exception.getMessage());
        log.error("resource not found", exception);
        return "not_found";
    }
}

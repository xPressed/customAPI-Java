package ru.xpressed.customapijava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.xpressed.customapijava.exception.ContactNotFoundException;

@Controller
public class ErrorController {
    @ExceptionHandler(Exception.class)
    public String showErrorPage() {
        return "error";
    }

    @ExceptionHandler(ContactNotFoundException.class)
    public void contactNotFound() {}
}

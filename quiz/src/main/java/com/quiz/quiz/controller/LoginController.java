package com.quiz.quiz.controller;

import com.quiz.quiz.dto.LoggedInResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class LoginController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public LoggedInResponse login() {

        return new LoggedInResponse().setMessage("Logged in");

    }
}

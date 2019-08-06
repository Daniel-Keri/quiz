package com.quiz.quiz.controller;

import com.quiz.quiz.dto.LoggedInResponse;
import com.quiz.quiz.dto.LoginFailedResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

@RestController
@RequestMapping("/")
public class LoginController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public LoggedInResponse login() {

        return new LoggedInResponse()
                .setSuccess(true)
                .setSessionId(RequestContextHolder.currentRequestAttributes().getSessionId())
                .setMessage("Logged in");
    }

//    @GetMapping("loginFailed")
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    public LoginFailedResponse loginFailed() {
//
//        return new LoginFailedResponse();
//    }
}

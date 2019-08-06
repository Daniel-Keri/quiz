package com.quiz.quiz.controller;

import com.quiz.quiz.dto.LoggedInResponse;
import com.quiz.quiz.dto.LoginFailedResponse;
import com.quiz.quiz.services.LoginService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public LoggedInResponse login() {

        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        String username = loginService.getLoggedIn(sessionId);

        if (username != null && (username != "anonymousUser" && username != "") ) {
            return new LoggedInResponse()
                    .setSuccess(true)
                    .setSessionId(sessionId)
                    .setMessage("Logged in");
        } else {
            return new LoggedInResponse()
                    .setSuccess(false)
                    .setSessionId(sessionId)
                    .setMessage("Not logged in");
        }
    }
}

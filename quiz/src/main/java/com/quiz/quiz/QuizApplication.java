package com.quiz.quiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

import java.util.Arrays;

@SpringBootApplication
public class QuizApplication {

	public static void main(String[] args) {

		SpringApplication.run(QuizApplication.class, args);
	}

}

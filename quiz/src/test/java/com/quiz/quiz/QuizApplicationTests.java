package com.quiz.quiz;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quiz.quiz.dto.question.ThemeResponse;
import io.restassured.RestAssured;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class QuizApplicationTests {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Value("${local.server.port}")
    private int port;

    @Before
    public void init() throws Exception {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    @WithMockUser(username = "asd@asd.asd", roles = "USER")
    public void test() throws Exception {

        List<ThemeResponse> questionList = given()
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .auth()
                .form("user@user.user", "user")
                .when()
                .log()
                .all()
                .get("/questions/themes")
                .body()
                .jsonPath()
                .get("content");





    }

}

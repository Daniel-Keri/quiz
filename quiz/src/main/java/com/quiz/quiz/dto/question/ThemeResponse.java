package com.quiz.quiz.dto.question;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ThemeResponse {

    private String theme;
}

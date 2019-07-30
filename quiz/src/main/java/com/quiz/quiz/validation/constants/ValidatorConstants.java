package com.quiz.quiz.validation.constants;

import java.util.regex.Pattern;

public class ValidatorConstants {

    public static final String EMAIL_REGEX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    public static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static final Integer MIN_CORRECT_ANSWER = 1;
    public static final Integer MAX_CORRECT_ANSWER = 1;
    public static final Integer MIN_ANSWER = 2;
    public static final Integer MAX_ANSWER = 8;
}

package com.quiz.quiz.entity.entityColumnNames;

public final class EntityColumnNames {

    public static final class TABLE {

        public static final String USER_ACCOUNT = _USER_ACCOUNT;
        public static final String ANSWERED_QUESTION = _ANSWERED_QUESTION;
    }

    private static final String _USER_ACCOUNT = "user_account";
    public static final class USER_ACCOUNT {

        public static final String ID = _USER_ACCOUNT + ".id";
        public static final String USERNAME = _USER_ACCOUNT + ".username";
        public static final String PASSWORD = _USER_ACCOUNT + ".password";
        public static final String EMAIL = _USER_ACCOUNT + ".email";
        public static final String ROLE = _USER_ACCOUNT + ".role";
    }

    private static final String _ANSWERED_QUESTION = "answered_questions";
    public static final class ANSWERED_QUESTION {

        public static final String ID = _ANSWERED_QUESTION + ".Id";
        public static final String QUESTION_ID = _ANSWERED_QUESTION + ".question_id";
        public static final String USER_ACCOUNT_ID = _ANSWERED_QUESTION + ".user_account_id";
        public static final String CHOSEN_ANSWER_ID = _ANSWERED_QUESTION + ".chosen_answer_id";
        public static final String IS_CORRECT = _ANSWERED_QUESTION + ".is_correct";
        public static final String POINTS = _ANSWERED_QUESTION + ".points";
        public static final String THEME = _ANSWERED_QUESTION + ".theme";
    }
}
package com.quiz.quiz.repository;

import com.quiz.quiz.entity.AnsweredQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface AnsweredQuestionsRepository extends JpaRepository<AnsweredQuestion, UUID> {

}

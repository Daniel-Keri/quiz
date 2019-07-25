package com.quiz.quiz.repository.accounts;

import com.quiz.quiz.entity.Account;
import com.quiz.quiz.entity.Question;
import com.quiz.quiz.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface UserAccountRepository extends JpaRepository<UserAccount, UUID> {

    Optional<UserAccount> findByEmail(String email);

//    @Query("SELECT aq " +
//            "FROM UserAccount ua JOIN ua.answeredQuestions aq " +
//            "WHERE " +
//            //"((KEY(aq) = :questionId " +
//            //"and aq = :userScore" +
//            //") " +
//            //"AND " +
//            "ua.id = :#{principal.id})")
//    //@Query("SELECT score FROM answered_questions WHERE ")
//    Map<UUID, Double> findAnsweredQuestions(/*@Param(value = "questionId") UUID questionId*//*, @Param(value = "userScore") Double userScore*/);

//    @Query("SELECT ua.id FROM UserAccount ua WHERE ua.id = :#{principal.id}")
//    Optional<UUID> getCurrentUserAccountId();
}

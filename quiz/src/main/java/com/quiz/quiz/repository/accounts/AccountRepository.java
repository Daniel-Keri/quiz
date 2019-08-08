package com.quiz.quiz.repository.accounts;

import com.quiz.quiz.entity.Account;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {

    @Query("SELECT a FROM com.quiz.quiz.entity.Account a WHERE a.email = ?1")
    Optional<Account> findByEmail(String email);

    @Query("SELECT a.id FROM com.quiz.quiz.entity.Account a WHERE a.id = :loggedInAccountId")
    Optional<UUID> getCurrentAccountId(@Param(value = "loggedInAccountId") UUID loggedInAccountId);

    @Query("SELECT a.hints FROM com.quiz.quiz.entity.Account a WHERE a.id = :loggedInUserAccountId")
    Optional<Integer> getMyHintCount(@Param(value = "loggedInUserAccountId") UUID loggedInUserAccountId);


    @Modifying
    @Query("UPDATE com.quiz.quiz.entity.Account a SET a.hints = :newHintCount WHERE a.id = :loggedInUserAccountId")
    Integer updateMyHintCount(@Param(value = "newHintCount") Integer newHintCount, @Param(value = "loggedInUserAccountId") UUID loggedInUserAccountId);
}

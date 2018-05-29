/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.api.repositories;

import com.gkenna.tullamoreqa.domain.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Page<Answer> findByQuestionId(Long questionId, Pageable pageable);

    Page<Answer> findByUserUsername(String username, Pageable pageable);

    Page<Answer> findByChosenAnswer(boolean chosenAnswer, Pageable pageable);

    @Query("SELECT DISTINCT a FROM Answer a INNER JOIN a.user u WHERE u.username = ?1")
    Page<Answer> findAnswersByUserUsername(String userUsername, Pageable pageable);
}
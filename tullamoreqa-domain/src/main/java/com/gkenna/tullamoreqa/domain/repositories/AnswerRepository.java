package com.gkenna.tullamoreqa.domain.repositories;

import com.gkenna.tullamoreqa.domain.Answer;
import com.gkenna.tullamoreqa.domain.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Page<Answer> findByQuestionId(Long questionId, Pageable pageable);
    Page<Answer> findByUserId(Long userId, Pageable pageable);
    Page<Answer> findByChosenAnswer(boolean chosenAnswer, Pageable pageable);
}
package com.gkenna.tullamoreqa.core.api.repositories;

import com.gkenna.tullamoreqa.domain.Answer;
import com.gkenna.tullamoreqa.domain.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Page<Answer> findByQuestionId(Long questionId, Pageable pageable);
    Page<Answer> findByUserUsername(String username, Pageable pageable);
    @Query("select distinct a from Answer a where a.user.username = :username")
    Page<Answer> findAnswersByUserUsername(@Param("username")String userUsername, Pageable pageable);
    Page<Answer> findByChosenAnswer(boolean chosenAnswer, Pageable pageable);

}
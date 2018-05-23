package com.gkenna.tullamoreqa.domain.repositories;

import com.gkenna.tullamoreqa.domain.Question;
import com.gkenna.tullamoreqa.domain.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    Page<Question> findByQuestionTitle(String title, Pageable pageable);
    Page<Question> findByUserId(Long userId, Pageable pageable);
    Page<Question> findByModifiedBy(Long modUserId, Pageable pageable);
    Page<Question> findByTagId(String tagId, Pageable pageable);
 }
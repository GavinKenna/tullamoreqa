package com.gkenna.tullamoreqa.domain.repositories;

import com.gkenna.tullamoreqa.domain.Question;
import com.gkenna.tullamoreqa.domain.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    Page<Question> findByTitle(String title, Pageable pageable);
    Page<Question> findByUserId(Long userId, Pageable pageable);
    Page<Question> findByModifiedBy(Long modUserId, Pageable pageable);
    Page<Question> getByTagsIn(Set<Tag> tags, Pageable pageable);

    /*@Query("SELECT n FROM Question n WHERE n.tags IN "
            + "SELECT t FROM Tag t  WHERE t.name in :super")*/
    //@Query("SELECT p FROM Question p WHERE p.tags IN (SELECT t FROM Tag t WHERE t.name IN (:super))")
    Collection<Question> findByTagsIn(@Param("super") List<String> tags);

    Collection<Question> findByTagsNameContaining(String tag);



 }
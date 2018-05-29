/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.api.repositories;

import com.gkenna.tullamoreqa.domain.Question;
import com.gkenna.tullamoreqa.domain.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Set;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    Page<Question> findByTitle(String title, Pageable pageable);

    Page<Question> findByUserUsername(String username, Pageable pageable);

    Page<Question> getByTagsIn(Set<Tag> tags, Pageable pageable);

    Page<Question> findQuestionsByUserUsername(String username, Pageable pageable);

    /**
     * This works, but much like the others above. This makes sure there's at least one tag that
     * matches. Still looking for one that makes sure it contains all tags
     *
     * @param tag
     * @return
     */
    @Query("select distinct q from Question q inner join q.tags t where t.name in :super")
    Collection<Question> findQuestionsBasedOnAnyTagName(@Param("super") Set<String> tag);

    /**
     * Find questions that contain all supplied tags.
     * We add 0L to the length to convert it to a long type.
     *
     * @param tags List of tags we wish to filter questions by.
     * @return 0 or more Questions that contain all Tags.
     */
    @Query("select q from Question q join q.tags t where t.name in :tags " +
            "group by q.id having count(q.id) = :#{#tags.length + 0L}")
    Collection<Question> findQuestionsBasedOnAllTagNames(@Param("tags") String[] tags);


}
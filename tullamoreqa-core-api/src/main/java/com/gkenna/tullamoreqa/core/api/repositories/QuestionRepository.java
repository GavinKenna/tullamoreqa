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

import java.math.BigInteger;
import java.util.Collection;
import java.util.Set;

/**
 * Repository for containing {@link Question}s. Will be called by the
 * responsible Service, in this case it will be
 * {@link com.gkenna.tullamoreqa.core.api.services.QuestionService}.
 *
 * @author Gavin Kenna
 * @since 0.0.0
 */
@Repository
public interface QuestionRepository extends JpaRepository<Question, BigInteger> {
    /**
     * Find a list of {@link Question}s whose Title matches the supplied Title.
     *
     * @param title    Title of the {@link Question}(s) to return.
     * @param pageable Potentially add Pagination.
     * @return List of {@link Question}s in Page form.
     */
    Page<Question> findByTitle(String title, Pageable pageable);

    /**
     * Return a list of all {@link Question}s associated with a particular
     * {@link com.gkenna.tullamoreqa.domain.User}.
     *
     * @param username Filter all  {@link Question}s based on this
     *                 {@link com.gkenna.tullamoreqa.domain.User} Username.
     * @param pageable Potentially add Pagination.
     * @return List of {@link Question}s in Page form.
     */
    Page<Question> findByUserUsername(String username, Pageable pageable);

    /**
     * Return a list of all {@link Question}s associated with a particular
     * {@link com.gkenna.tullamoreqa.domain.Tag}.
     *
     * @param tags     Filter all  {@link Question}s based on this
     *                 {@link com.gkenna.tullamoreqa.domain.Tag} Username.
     * @param pageable Potentially add Pagination.
     * @return List of {@link Question}s in Page form.
     */
    Page<Question> getByTagsIn(Set<Tag> tags, Pageable pageable);

    /*
    TODO This may be a duplication.
     */

    /**
     * Return a list of all {@link Question}s associated with a particular
     * {@link com.gkenna.tullamoreqa.domain.User}.
     *
     * @param username Filter all  {@link Question}s based on this
     *                 {@link com.gkenna.tullamoreqa.domain.User} Username.
     * @param pageable Potentially add Pagination.
     * @return List of {@link Question}s in Page form.
     */
    Page<Question> findQuestionsByUserUsername(
            String username, Pageable pageable);

    /**
     * This works, but much like the others above. This makes sure there's at
     * least one tag that matches. Still looking for one that makes sure it
     * contains all tags.
     *
     * @param tag List of tags that the Question may contain.
     * @return Collection of Questions that may have supplied Tags.
     */
    @Query("SELECT DISTINCT q FROM Question q INNER JOIN q.tags t "
            + "WHERE t.name IN :super")
    Collection<Question> findQuestionsBasedOnAnyTagName(
            @Param("super") Set<String> tag);

    /**
     * Find questions that contain all supplied tags.
     * We add 0L to the length to convert it to a long type.
     *
     * @param tags List of tags we wish to filter questions by.
     * @return 0 or more Questions that contain all Tags.
     */
    @Query("SELECT q FROM Question q JOIN q.tags t WHERE t.name IN :tags "
            + "GROUP BY q.id HAVING COUNT(q.id) = :#{#tags.length + 0L}")
    Collection<Question> findQuestionsBasedOnAllTagNames(
            @Param("tags") String[] tags);
}

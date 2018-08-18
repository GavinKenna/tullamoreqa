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

import java.math.BigInteger;

/**
 * Repository for containing {@link Answer}s. Will be called by the responsible
 * Service, in this case it will be
 * {@link com.gkenna.tullamoreqa.core.api.services.AnswerService}.
 *
 * @author Gavin Kenna
 * @since 0.0.0
 */
@Repository
public interface AnswerRepository extends JpaRepository<Answer, BigInteger> {
    /**
     * Return a list of all {@link Answer}s associated with a particular
     * {@link com.gkenna.tullamoreqa.domain.Question}.
     *
     * @param questionId Filter all  {@link Answer}s based on this
     *                   {@link com.gkenna.tullamoreqa.domain.Question} ID
     * @param pageable   Potentially add Pagination.
     * @return List of {@link Answer}s in Page form.
     */
    Page<Answer> findByQuestionId(BigInteger questionId, Pageable pageable);

    /**
     * Return a list of all {@link Answer}s associated with a particular
     * {@link com.gkenna.tullamoreqa.domain.User}.
     *
     * @param username Filter all  {@link Answer}s based on this
     *                 {@link com.gkenna.tullamoreqa.domain.User} Username.
     * @param pageable Potentially add Pagination.
     * @return List of {@link Answer}s in Page form.
     */
    Page<Answer> findByUserUsername(String username, Pageable pageable);

    /**
     * Return a list of all {@link Answer}s based on their Chosen Answer
     * flag for their associated
     * {@link com.gkenna.tullamoreqa.domain.Question}.
     *
     * @param chosenAnswer If the {@link Answer} is the Chosen Answer or not.
     * @param pageable     Potentially add Pagination.
     * @return List of {@link Answer}s in Page form.
     */
    Page<Answer> findByChosenAnswer(boolean chosenAnswer, Pageable pageable);

    /**
     * Return a list of all {@link Answer}s associated with a particular
     * {@link com.gkenna.tullamoreqa.domain.User}.
     *
     * @param userUsername Filter all  {@link Answer}s based on this
     *                     {@link com.gkenna.tullamoreqa.domain.User} Username.
     * @param pageable     Potentially add Pagination.
     * @return List of {@link Answer}s in Page form.
     */
    @Query("SELECT DISTINCT a FROM Answer a INNER JOIN a.user u "
            + "WHERE u.username = ?1")
    Page<Answer> findAnswersByUserUsername(
            String userUsername, Pageable pageable);

    /**
     * Return a list of all {@link Answer}s associated with a particular
     * {@link com.gkenna.tullamoreqa.domain.User}.
     *
     * @param userUsername Filter all  {@link Answer}s based on this
     *                     {@link com.gkenna.tullamoreqa.domain.User} Username.
     * @return Array of {@link Answer}s.
     */
    @Query("SELECT DISTINCT a FROM Answer a INNER JOIN a.user u "
           + "WHERE u.username = ?1")
    Answer[] findAnswersByUserUsername(String userUsername);

    /**
     * Return an {@link Answer} based on its Description body.
     *
     * @param body {@link Answer} body.
     * @return {@link Answer} that contains the received Body.
     */
    Answer findAnswerByBody(String body);
}

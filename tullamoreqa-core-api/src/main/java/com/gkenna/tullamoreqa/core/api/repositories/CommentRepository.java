/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.api.repositories;

import com.gkenna.tullamoreqa.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

/**
 * Repository for containing {@link Comment}s. Will be called by the responsible
 * Service, in this case it will be
 * {@link com.gkenna.tullamoreqa.core.api.services.CommentService}.
 *
 * @author Gavin Kenna
 * @since 0.0.0
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, BigInteger> {
}

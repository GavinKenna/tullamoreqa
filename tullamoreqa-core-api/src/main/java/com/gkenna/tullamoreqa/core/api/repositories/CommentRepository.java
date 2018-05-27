package com.gkenna.tullamoreqa.core.api.repositories;

import com.gkenna.tullamoreqa.domain.Comment;
import com.gkenna.tullamoreqa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
 }
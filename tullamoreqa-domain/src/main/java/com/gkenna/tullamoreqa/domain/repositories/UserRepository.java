package com.gkenna.tullamoreqa.domain.repositories;

import com.gkenna.tullamoreqa.domain.Question;
import com.gkenna.tullamoreqa.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
 }
/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.api.repositories;

import com.gkenna.tullamoreqa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



/**
 * Repository for containing {@link User}s. Will be called by the responsible
 * Service, in this case it will be
 * {@link com.gkenna.tullamoreqa.core.api.services.UserService}.
 *
 * @author Gavin Kenna
 * @since 0.0.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Return a {@link User} based on its Username.
     *
     * @param username Username of the {@link User}
     * @return {@link User} with the particular Username.
     */
    User findByUsername(String username);
}

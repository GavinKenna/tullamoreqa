/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.core.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Main execution spot for TullamoreQA. This file carries out all
 * of the auto-wiring.
 *
 * @author Gavin Kenna
 * @since 0.0.0
 */
@SpringBootApplication(scanBasePackages = "com.gkenna.tullamoreqa.*")
@ComponentScan({"com.gkenna.tullamoreqa.*"})
@EnableJpaRepositories("com.gkenna.tullamoreqa.core.api.repositories")
@EntityScan("com.gkenna.tullamoreqa.domain")
@EnableTransactionManagement
public class Application {

    /**
     * Logger for Application.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(Application.class);

    /**
     * Start the Spring Application.
     *
     * @param args Any additional arguments to be passed to Spring.
     */
    public static final void main(final String[] args) {
        SpringApplication.run(
                com.gkenna.tullamoreqa.core.impl.Application.class);
    }

    /**
     * Might be removed.
     *
     * @return Nothing.
     */
    @Bean
    @Transactional
    @SuppressWarnings("checkstyle:DesignForExtension")
    public CommandLineRunner demo() {
        return (args) -> {

        };
    }
}

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

@SpringBootApplication(scanBasePackages = "com.gkenna.tullamoreqa.*")
@ComponentScan({"com.gkenna.tullamoreqa.*"})
@EnableJpaRepositories("com.gkenna.tullamoreqa.core.api.repositories")
@EntityScan("com.gkenna.tullamoreqa.domain")
@EnableTransactionManagement
public class Application {

    private static final Logger LOGGER = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(com.gkenna.tullamoreqa.core.impl.Application.class);
    }


    @Bean
    @Transactional
    public CommandLineRunner demo() {
        return (args) -> {

        };
    }


}
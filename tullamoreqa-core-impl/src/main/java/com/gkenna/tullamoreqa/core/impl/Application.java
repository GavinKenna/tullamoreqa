package com.gkenna.tullamoreqa.core.impl;

import com.gkenna.tullamoreqa.domain.service.QuestionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.gkenna.tullamoreqa.*"})
@ComponentScan({"com.gkenna.tullamoreqa.*"})
@EnableJpaRepositories("com.gkenna.tullamoreqa.domain.repositories")
@EntityScan("com.gkenna.tullamoreqa.domain")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(com.gkenna.tullamoreqa.core.impl.Application.class);
    }

    @Bean
    public CommandLineRunner demo(QuestionService questionService) {
        return (args) -> {
            questionService.addQuestion();
        };
    }


}
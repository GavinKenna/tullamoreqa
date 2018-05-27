package com.gkenna.tullamoreqa.core.impl;

import com.gkenna.tullamoreqa.core.api.repositories.AnswerRepository;
import com.gkenna.tullamoreqa.core.api.repositories.QuestionRepository;
import com.gkenna.tullamoreqa.core.api.repositories.TagRepository;
import com.gkenna.tullamoreqa.core.api.repositories.UserRepository;
import com.gkenna.tullamoreqa.core.api.services.*;
import com.gkenna.tullamoreqa.domain.Answer;
import com.gkenna.tullamoreqa.domain.Question;
import com.gkenna.tullamoreqa.domain.Tag;
import com.gkenna.tullamoreqa.domain.User;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@SpringBootApplication(scanBasePackages = "com.gkenna.tullamoreqa.*" )
@ComponentScan({"com.gkenna.tullamoreqa.*"})
@EnableJpaRepositories("com.gkenna.tullamoreqa.core.api.repositories")
@EntityScan("com.gkenna.tullamoreqa.domain")
@EnableTransactionManagement
public class Application {

    @Autowired
    TagService tagService;

    public static void main(String[] args) {
        SpringApplication.run(com.gkenna.tullamoreqa.core.impl.Application.class);
    }




    @Bean
    @Transactional
    public CommandLineRunner demo() {
        return (args) -> {
            tagService.addTag(new Tag("G"));

        };
    }



}
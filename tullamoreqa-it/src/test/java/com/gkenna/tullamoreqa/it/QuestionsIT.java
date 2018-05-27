package com.gkenna.tullamoreqa.it;

import com.gkenna.tullamoreqa.core.api.repositories.AnswerRepository;
import com.gkenna.tullamoreqa.core.api.repositories.QuestionRepository;
import com.gkenna.tullamoreqa.core.api.repositories.TagRepository;
import com.gkenna.tullamoreqa.core.api.repositories.UserRepository;
import com.gkenna.tullamoreqa.core.api.services.*;
import com.gkenna.tullamoreqa.core.impl.services.QuestionServiceImpl;
import com.gkenna.tullamoreqa.domain.Answer;
import com.gkenna.tullamoreqa.domain.Question;
import com.gkenna.tullamoreqa.domain.Tag;
import com.gkenna.tullamoreqa.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AppConfiguration.class})
public class QuestionsIT {

    @Autowired
    QuestionService questionService;
    @Autowired
    AnswerService answerService;
    @Autowired
    UserService userService;
    @Autowired
    TagService tagService;
    @Autowired
    CommentService commentService;

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    TagRepository tagRepository;

    @Test
    public void main() {

        createTags();
        createUsers();
        createQuestions();
        createAnswers();

        queryDb();
    }

    private void queryDb() {
        Page<Answer> answersByGavin = answerRepository.findAnswersByUserUsername("Gavin", Pageable.unpaged());
        System.out.println("Answer size by Gavin == " + answersByGavin.getSize());
        assert answersByGavin.getSize() == 1;

        Page<Question> questionsByGavin = questionRepository.findQuestionsByUser_Username("Gavin", Pageable.unpaged());
        assert questionsByGavin.getSize() == 1;

        List<Question> questionsByJavaTag = (List<Question>) questionRepository.findQuestionsBasedOnAllTagNames
                (new String[]{"Java"});
        assert questionsByJavaTag.size() == 2;

        List<Question> questionsByJavaAndHelpTag = (List<Question>) questionRepository
                .findQuestionsBasedOnAllTagNames(new String[]{"Java", "Help"});
        assert questionsByJavaAndHelpTag.size() == 1;

    }

    private void createAnswers() {
        Question help = questionRepository.findByTitle("Help", Pageable.unpaged()).iterator().next();
        Question halp = questionRepository.findByTitle("Halp", Pageable.unpaged()).iterator().next();

        Answer a = new Answer();
        a.setUser(userRepository.findByUsername("Alice"));
        a.setQuestion(help);
        a.setBody("I know the answer!");
        answerService.addAnswer(a);

        Answer b = new Answer();
        b.setUser(userRepository.findByUsername("Bob"));
        b.setQuestion(help);
        b.setBody("No, I know it!");
        answerService.addAnswer(b);


        Answer c = new Answer();
        c.setUser(userRepository.findByUsername("Gavin"));
        c.setQuestion(halp);
        c.setBody("I don't know anything");
        answerService.addAnswer(c);
    }

    private void createQuestions() {

        Optional<Tag> java = tagRepository.findById("Java");
        Optional<Tag> help = tagRepository.findById("Help");
        Optional<Tag> some = tagRepository.findById("SoemthingElse");

        Question question = new Question();
        question.setBody("Help help help");
        question.setTitle("Help");
        question.setUser(userRepository.findByUsername("Gavin"));
        question.getTags().add(java.get());
        questionService.addQuestion(question);

        Question questionTwo = new Question();
        questionTwo.setBody("HAAAALP");
        questionTwo.setTitle("Halp");
        questionTwo.setUser(userRepository.findByUsername("Alice"));
        questionTwo.getTags().add(help.get());
        questionTwo.getTags().add(java.get());
        questionService.addQuestion(questionTwo);
    }

    private void createUsers() {
        User gavin = new User("Gavin");

        userService.addUser(gavin);

        User bob = new User("Bob");

        userService.addUser(bob);

        User alice = new User("Alice");

        userService.addUser(alice);
    }

    private void createTags() {
        Tag java = new Tag("Java");
        Tag help = new Tag("Help");
        Tag somethingElse = new Tag("SomethingElse");

        tagService.addTag(java);
        tagService.addTag(help);
        tagService.addTag(somethingElse);

        assert tagService.doesTagExist("Java")==true;
        assert tagService.doesTagExist("Help")==true;
        assert tagService.doesTagExist("SomethingElse");

    }

}

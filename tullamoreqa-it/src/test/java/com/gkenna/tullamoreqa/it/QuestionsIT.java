package com.gkenna.tullamoreqa.it;

import com.gkenna.tullamoreqa.core.api.repositories.AnswerRepository;
import com.gkenna.tullamoreqa.core.api.repositories.QuestionRepository;
import com.gkenna.tullamoreqa.core.api.repositories.TagRepository;
import com.gkenna.tullamoreqa.core.api.repositories.UserRepository;
import com.gkenna.tullamoreqa.core.api.services.*;
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
        System.out.println("Answer size by Gavin == " + answersByGavin.getTotalPages());
        assert answersByGavin.hasContent();

        Page<Question> questionsByGavin = questionRepository.findQuestionsByUserUsername("Gavin", Pageable.unpaged());
        assert questionsByGavin.hasContent();

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

        assert help != null;
        assert halp != null;

        assert answerRepository.findAll().size() == 0;

        User gavin = userRepository.findByUsername("Gavin");
        User bob = userRepository.findByUsername("Bob");
        User alice = userRepository.findByUsername("Alice");

        Answer a = new Answer(help, alice, "I know the Answer");
        answerService.addAnswer(a);

        assert answerRepository.findAll().size() == 1;

        Answer b = new Answer(help, bob, "No, I know it!");
        answerService.addAnswer(b);

        assert answerRepository.findAll().size() == 2;

        Answer c = new Answer(halp, gavin, "I don't know anything.");
        answerService.addAnswer(c);

        System.out.println("Gavins Answer " + c.toString());

        assert answerRepository.findAll().size() == 3;
    }

    private void createQuestions() {

        Optional<Tag> java = tagRepository.findById("Java");
        Optional<Tag> help = tagRepository.findById("Help");
        Optional<Tag> some = tagRepository.findById("SoemthingElse");

        assert questionRepository.findAll().size() == 0;

        Question question = new Question();
        question.setBody("Help help help");
        question.setTitle("Help");
        question.setUser(userRepository.findByUsername("Gavin"));
        question.getTags().add(java.get());
        questionService.addQuestion(question);

        assert questionRepository.findAll().size() == 1;

        Question questionTwo = new Question();
        questionTwo.setBody("HAAAALP");
        questionTwo.setTitle("Halp");
        questionTwo.setUser(userRepository.findByUsername("Alice"));
        questionTwo.getTags().add(help.get());
        questionTwo.getTags().add(java.get());
        questionService.addQuestion(questionTwo);

        assert questionRepository.findAll().size() == 2;
    }

    private void createUsers() {
        User gavin = new User("Gavin");

        userService.addUser(gavin);

        User bob = new User("Bob");

        userService.addUser(bob);

        User alice = new User("Alice");

        userService.addUser(alice);

        assert userService.doesUserExist("Gavin") == true;
        assert userService.doesUserExist("Bob") == true;
        assert userService.doesUserExist("Alice") == true;
    }

    private void createTags() {
        Tag java = new Tag("Java");
        Tag help = new Tag("Help");
        Tag somethingElse = new Tag("SomethingElse");

        tagService.addTag(java);
        tagService.addTag(help);
        tagService.addTag(somethingElse);

        assert tagService.doesTagExist("Java") == true;
        assert tagService.doesTagExist("Help") == true;
        assert tagService.doesTagExist("SomethingElse");

    }

}

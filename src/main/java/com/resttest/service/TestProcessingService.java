package com.resttest.service;

import com.resttest.dto.test.TestDto;
import com.resttest.dto.testaccess.TestAccessDto;
import com.resttest.dto.testprocessing.TestProcessingDto;
import com.resttest.model.*;
import com.resttest.repository.*;
import com.resttest.utils.TestProcessingUtils;
import com.resttest.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Created by Владислав on 28.03.2017.
 */
@Service
@Transactional
public class TestProcessingService {

    @Autowired
    private TestJpaRepository testJpaRepository;

    @Autowired
    private TestAccessJpaRepository testAccessJpaRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TestProcessingUtils testProcessingUtils;

    @Autowired
    private AnswerRepresentJpaRepository answerRepresentJpaRepository;

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private TestProcessingJpaRepository jpaRepository;

    @Autowired
    private TestProcessingUtils utils;

    public TestProcessingDto getTestProcessing(Long testAccessId) {
        TestProcessing result;
        if(creationControl(testAccessId).getId() == null) {
            TestProcessing entity = createTestProcessing(testAccessId);
            entity.getQuestionsRepresent().forEach(s -> s.getAnswers().forEach(j -> answerRepresentJpaRepository.save(j)));
            return utils.convertEntityToDto(jpaRepository.getOne(entity.getId()));
        } else {
            result = creationControl(testAccessId);
            return utils.convertEntityToDto(jpaRepository.getOne(result.getId()));
        }
    }

    private TestProcessing createTestProcessing(Long testAccessId) {
        TestAccess testAccess = testAccessJpaRepository.getOne(testAccessId);
        Test currentTest = testAccess.getTest();
        TestProcessing testProcessing = new TestProcessing();
        setIndices(currentTest);
        List<QuestionRepresent> questionRepresents = new ArrayList<>();
        currentTest.getQuestions().forEach(s -> questionRepresents.add(createQuestionRepresent(s)));
        Collections.shuffle(questionRepresents);
        testProcessing.setTestAccess(testAccess);
        testProcessing.setTest(currentTest);
        testProcessing.setQuestionsRepresent(questionRepresents);
        testProcessing.setPassingUser(userJpaRepository.getOne(userService.getCurrentAuthenticatedUser().getId()));
        return jpaRepository.save(testProcessing);
    }

    private void setIndices(Test test) {
        final Integer[] qId = {0};
        test.getQuestions().forEach(s -> {
            final Integer[] aId = {0};
            s.setSerialNumber(qId[0]++);
            s.getAnswers().forEach(j -> {
                j.setSerialNumber(aId[0]++);
            });
        });
        testJpaRepository.saveAndFlush(test);
    }

    private QuestionRepresent createQuestionRepresent(Question question) {
        QuestionRepresent questionRepresent = new QuestionRepresent();
        List<AnswerRepresent> answerRepresents = new ArrayList<>();
        question.getAnswers().forEach(s -> answerRepresents.add(createAnswerRepresent(s, questionRepresent)));
        questionRepresent.setQuestion(question);
        questionRepresent.setSerialNumber(question.getSerialNumber());
        questionRepresent.setAnswered(false);
        Collections.shuffle(answerRepresents);
        questionRepresent.setAnswers(answerRepresents);
        return questionRepresent;
    }

    private AnswerRepresent createAnswerRepresent(Answer answer, QuestionRepresent questionRepresent) {
        AnswerRepresent answerRepresent = new AnswerRepresent();
        answerRepresent.setSerialNumber(answer.getSerialNumber());
        answerRepresent.setAnswer(answer.getAnswer());
        answerRepresent.setChosen(false);
        answerRepresent.setQuestion(questionRepresent);
        answerRepresent.setType(answer.getType().toString());
        return answerRepresent;
    }

    private TestProcessing creationControl(Long testAccessId) {
        Long userId = userService.getCurrentAuthenticatedUser().getId();
        TestAccess currentTestAccess = testAccessJpaRepository.getOne(testAccessId);
        List<TestProcessing> allTestProcessing = jpaRepository.findAll();
        TestProcessing result = new TestProcessing();
        for (int i = 0; i < allTestProcessing.size(); i++) {
            if(allTestProcessing.get(i).getPassingUser().getId() == userId &&
                    allTestProcessing.get(i).getTestAccess().getId() == testAccessId &&
                    allTestProcessing.get(i).getTest().getId() == currentTestAccess.getTest().getId()) {
                result = allTestProcessing.get(i);
            }
        }
        return result;
    }

}

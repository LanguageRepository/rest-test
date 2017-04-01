package com.resttest.service;

import com.resttest.dto.testprocessing.TestProcessingDto;
import com.resttest.model.*;
import com.resttest.repository.QuestionRepresentJpaRepository;
import com.resttest.repository.TestAccessJpaRepository;
import com.resttest.repository.TestJpaRepository;
import com.resttest.repository.TestProcessingJpaRepository;
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
    private TestProcessingUtils utils;

    @Autowired
    private TestJpaRepository testJpaRepository;

    @Autowired
    private TestProcessingJpaRepository jpaRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserUtils userUtils;

    @Autowired
    private TestAccessJpaRepository testAccessJpaRepository;

    @Autowired
    private QuestionRepresentJpaRepository questionRepresentJpaRepository;

    @Transactional
    public TestProcessingDto getTestProcessingByTestId(Long testId, Long testAccessId) {
        TestProcessing entity;
        if(creationControl(testId, testAccessId).getId() == null) {
            entity = createTestProcessing(testJpaRepository.getOne(testId),
                    testAccessJpaRepository.getOne(testAccessId));
        } else {
            entity = creationControl(testId, testAccessId);
        }
        TestProcessingDto result = utils.convertEntityToDto(jpaRepository.getOne(entity.getId()));
        Collections.shuffle(result.getQuestions());
        result.getQuestions().forEach(s -> Collections.shuffle(s.getAnswers()));
        return result;
    }

    @Transactional
    private TestProcessing createTestProcessing(Test test, TestAccess testAccess) {
        setIndicies(test);
        TestProcessing testProcessing = new TestProcessing();
        List<QuestionRepresent> questionRepresents = new ArrayList<>();
        test.getQuestions().forEach(s -> questionRepresents.add(createQuestionRepresent(s)));
        testProcessing.setTest(test);
        testProcessing.setName(test.getName());
        testProcessing.setPassingUser(userUtils.convertDtoToEntityForPut(userService.getCurrentAuthenticatedUser()));
        testProcessing.setQuestionsRepresent(questionRepresents);
        testProcessing.setTestAccess(testAccess);
        return jpaRepository.saveAndFlush(testProcessing);
    }

    private void setIndicies(Test test) {
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
        questionRepresent.setQuestion(question);
        questionRepresent.setSerialNumber(question.getSerialNumber());
        return questionRepresentJpaRepository.saveAndFlush(questionRepresent);
    }

    private TestProcessing creationControl(Long testId, Long taskAccessId) {
        Long currentUserId = userService.getCurrentAuthenticatedUser().getId();
        List<TestProcessing> allTestProcessing = jpaRepository.findAll();
        TestProcessing result = new TestProcessing();
        for (TestProcessing anAllTestProcessing : allTestProcessing) {
            if (Objects.equals(anAllTestProcessing.getPassingUser().getId(), currentUserId) &&
                    Objects.equals(anAllTestProcessing.getTest().getId(), testId) &&
                    Objects.equals(anAllTestProcessing.getTestAccess().getId(), taskAccessId)) {
                result = anAllTestProcessing;
            }
        }
        return result;
    }

}

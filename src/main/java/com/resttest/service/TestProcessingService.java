package com.resttest.service;

import com.resttest.dto.testprocessing.TestProcessingDto;
import com.resttest.model.Question;
import com.resttest.model.QuestionRepresent;
import com.resttest.model.Test;
import com.resttest.model.TestProcessing;
import com.resttest.repository.QuestionRepresentJpaRepository;
import com.resttest.repository.TestJpaRepository;
import com.resttest.repository.TestProcessingJpaRepository;
import com.resttest.utils.QuestionRepresentUtils;
import com.resttest.utils.TestProcessingUtils;
import com.resttest.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    private QuestionRepresentUtils questionRepresentUtils;

    @Autowired
    private QuestionRepresentJpaRepository questionRepresentJpaRepository;

    @Transactional
    public TestProcessingDto getTestProcessingByTestId(Long testId) {
        TestProcessing entity = createTestProcessing(testJpaRepository.getOne(testId));
        TestProcessingDto result = utils.convertEntityToDto(jpaRepository.getOne(entity.getId()));
        Collections.shuffle(result.getQuestions());
        result.getQuestions().forEach(s -> Collections.shuffle(s.getAnswers()));
        return result;
    }

    @Transactional
    private TestProcessing createTestProcessing(Test test) {
        setIndicies(test);
        TestProcessing testProcessing = new TestProcessing();
        List<QuestionRepresent> questionRepresents = new ArrayList<>();
        test.getQuestions().forEach(s -> questionRepresents.add(createQuestionRepresent(s)));
        testProcessing.setTest(test);
        testProcessing.setName(test.getName());
        testProcessing.setPassingUser(userUtils.convertDtoToEntityForPut(userService.getCurrentAuthenticatedUser()));
        testProcessing.setQuestionsRepresent(questionRepresents);
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

}

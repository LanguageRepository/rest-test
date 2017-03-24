package com.resttest.service;

import com.resttest.dto.testpassage.TestPassageDto;
import com.resttest.dto.question.QuestionDto;
import com.resttest.dto.test.TestDto;
import com.resttest.dto.user.UserDto;
import com.resttest.model.Question;
import com.resttest.model.Test;
import com.resttest.model.TestPassage;
import com.resttest.model.User;
import com.resttest.repository.QuestionJpaRepository;
import com.resttest.repository.TestJpaRepository;
import com.resttest.repository.TestPassageJpaRepository;
import com.resttest.utils.QuestionUtils;
import com.resttest.utils.TestPassageUtils;
import com.resttest.utils.TestUtils;
import com.resttest.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * Created by Владислав on 23.03.2017.
 */
@Service
@Transactional
public class TestPassageService {

    @Autowired
    private TestPassageJpaRepository jpaRepository;

    @Autowired
    private QuestionJpaRepository questionJpaRepository;

    @Autowired
    private TestJpaRepository testJpaRepository;

    @Autowired
    private QuestionUtils questionUtils;

    @Autowired
    private UserUtils userUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private TestPassageUtils utils;

    @Transactional
    public TestPassageDto getTestPassage(Long testId) {
        TestPassage currentTestPassage = createTestPassage(testId,
                userUtils.convertDtoToEntityForPut(userService.getCurrentAuthenticatedUser()));
        TestPassageDto dto = utils.convertEntityToDto(currentTestPassage);
        Collections.shuffle(dto.getQuestionDtos());
        dto.getQuestionDtos().forEach(s -> Collections.shuffle(s.getAnswerDtos()));
        return dto;
    }

    @Transactional
    private TestPassage createTestPassage(Long testId, User passingUser) {
        TestPassage entity = new TestPassage();
        Test currentTest = testJpaRepository.getOne(testId);
        List<Question> questionEntities = questionJpaRepository.findQuestionByTestId(testId);
        entity.setTest(currentTest);
        entity.setPassingUser(passingUser);
        entity.setQuestions(questionEntities);
        entity.setName(currentTest.getName());
        return jpaRepository.saveAndFlush(entity);
    }

}

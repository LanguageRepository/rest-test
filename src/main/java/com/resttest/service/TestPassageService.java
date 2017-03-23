package com.resttest.service;

import com.resttest.dto.TestPassageDto;
import com.resttest.dto.user.UserDto;
import com.resttest.model.Test;
import com.resttest.model.TestPassage;
import com.resttest.model.User;
import com.resttest.repository.TestJpaRepository;
import com.resttest.repository.TestPassageJpaRepository;
import com.resttest.utils.TestPassageUtils;
import com.resttest.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

/**
 * Created by Владислав on 23.03.2017.
 */
@Service
@Transactional
public class TestPassageService {

    @Autowired
    private TestJpaRepository testJpaRepository;

    @Autowired
    private TestPassageJpaRepository jpaRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserUtils userUtils;

    @Autowired
    private TestPassageUtils utils;

    public TestPassageDto getTestPassage(Long id) {
        final Long[] i = {0l};
        final Long[] j = {0l};
        final UserDto[] currentUser = {userService.getCurrentAuthenticatedUser()};
        TestPassage currentEntity = createTestPassage(id, userUtils.convertDtoToEntityForPut(currentUser[0]));
        currentEntity.getQuestions().forEach(s -> {
            s.setId(i[0]++);
            s.getAnswers().forEach(e -> e.setId(j[0]++));
        });
        return utils.convertEntityToDto(currentEntity);
    }

    private TestPassage createTestPassage(Long testId, User passingUser) {
        TestPassage testPassage = new TestPassage();
        Test currentTest = testJpaRepository.getOne(testId);
        Collections.shuffle(currentTest.getQuestions());
        currentTest.getQuestions().forEach(s -> Collections.shuffle(s.getAnswers()));
        testPassage.setTest(currentTest);
        testPassage.setName(currentTest.getName());
        testPassage.setPassingUser(passingUser);
        testPassage.setQuestions(currentTest.getQuestions());
        return jpaRepository.save(testPassage);
    }

}

package com.resttest.dto.testpassage;

import com.resttest.dto.question.QuestionDto;
import com.resttest.dto.question.QuestionDtoForMainModule;
import com.resttest.dto.user.UserDto;
import com.resttest.dto.user.UserDtoForMainModule;

import java.util.List;

/**
 * Created by Владислав on 23.03.2017.
 */
public class TestPassageDto {

    private Long id;

    private String name;

    private Long testId;

    private List<QuestionDtoForMainModule> questionDtos;

    private UserDtoForMainModule passingUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public List<QuestionDtoForMainModule> getQuestionDtos() {
        return questionDtos;
    }

    public void setQuestionDtos(List<QuestionDtoForMainModule> questionDtos) {
        this.questionDtos = questionDtos;
    }

    public UserDtoForMainModule getPassingUser() {
        return passingUser;
    }

    public void setPassingUser(UserDtoForMainModule passingUser) {
        this.passingUser = passingUser;
    }
}

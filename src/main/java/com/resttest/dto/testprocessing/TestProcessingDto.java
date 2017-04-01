package com.resttest.dto.testprocessing;

import com.resttest.dto.QuestionRepresentDto;
import com.resttest.dto.ShortView;
import com.resttest.dto.question.SimpleQuestionDto;
import com.resttest.dto.test.SimpleTestDto;
import com.resttest.dto.testaccess.TestAccessDto;
import com.resttest.model.TestAccess;

import java.util.List;

/**
 * Created by Владислав on 28.03.2017.
 */
public class TestProcessingDto {

    private Long id;

    private String name;

    private SimpleTestDto test;

    private List<QuestionRepresentDto> questions;

    private ShortView passingUser;

    private ShortView testAccess;

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

    public SimpleTestDto getTest() {
        return test;
    }

    public void setTest(SimpleTestDto test) {
        this.test = test;
    }

    public List<QuestionRepresentDto> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionRepresentDto> questions) {
        this.questions = questions;
    }

    public ShortView getPassingUser() {
        return passingUser;
    }

    public void setPassingUser(ShortView passingUser) {
        this.passingUser = passingUser;
    }

    public ShortView getTestAccess() {
        return testAccess;
    }

    public void setTestAccess(ShortView testAccess) {
        this.testAccess = testAccess;
    }
}

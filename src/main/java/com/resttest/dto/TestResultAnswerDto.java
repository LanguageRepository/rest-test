package com.resttest.dto;

import com.resttest.model.Answer;
import com.resttest.model.TestResultQuestion;

/**
 * Created by kvasa on 01.01.2017.
 */
public class TestResultAnswerDto {

    private Long id;
    private TestResultQuestion testResultQuestion;
    private Answer answer;
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TestResultQuestion getTestResultQuestion() {
        return testResultQuestion;
    }

    public void setTestResultQuestion(TestResultQuestion testResultQuestion) {
        this.testResultQuestion = testResultQuestion;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

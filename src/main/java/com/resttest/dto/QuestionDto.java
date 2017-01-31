package com.resttest.dto;

import java.util.List;

/**
 * Created by kvasa on 01.01.2017.
 */
public class QuestionDto {

    private Long id;
    private String question;
    private List<AnswerDto> answers;
    private Long testId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<AnswerDto> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDto> answers) {
        this.answers = answers;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }
}


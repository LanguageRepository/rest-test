package com.resttest.dto;

import com.resttest.model.Question;

/**
 * Created by kvasa on 01.01.2017.
 */
public class AnswerDto {

    private Long id;
    private String answer;
    private Question question;
    private String rightValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getRightValue() {
        return rightValue;
    }

    public void setRightValue(String rightValue) {
        this.rightValue = rightValue;
    }
}

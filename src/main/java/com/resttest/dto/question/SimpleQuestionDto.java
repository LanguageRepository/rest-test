package com.resttest.dto.question;

import com.resttest.dto.answer.SimpleAnswerDto;

import java.util.List;

/**
 * Created by Владислав on 28.03.2017.
 */
public class SimpleQuestionDto {

    private int serialNumber;

    private String question;

    private List<SimpleAnswerDto> answers;

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<SimpleAnswerDto> getAnswers() {
        return answers;
    }

    public void setAnswers(List<SimpleAnswerDto> answers) {
        this.answers = answers;
    }
}

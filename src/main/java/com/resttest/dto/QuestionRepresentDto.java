package com.resttest.dto;

import com.resttest.dto.answer.AnswerDto;
import com.resttest.dto.answer.SimpleAnswerDto;
import com.resttest.dto.answerrepresent.AnswerRepresentDto;
import com.resttest.dto.question.QuestionDto;

import java.util.List;

/**
 * Created by Владислав on 27.03.2017.
 */
public class QuestionRepresentDto {

    private Long id;

    private String question;

    private List<AnswerRepresentDto> answers;

    private int serialNumber;

    private String type;

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

    public List<AnswerRepresentDto> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerRepresentDto> answers) {
        this.answers = answers;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

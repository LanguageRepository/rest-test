package com.resttest.dto.question;

import com.resttest.dto.answer.AnswerDtoForMainModule;

import java.util.List;

/**
 * Created by Владислав on 24.03.2017.
 */
public class QuestionDtoForMainModule {

    private Long id;

    private String question;

    private List<AnswerDtoForMainModule> answerDtos;

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

    public List<AnswerDtoForMainModule> getAnswerDtos() {
        return answerDtos;
    }

    public void setAnswerDtos(List<AnswerDtoForMainModule> answerDtos) {
        this.answerDtos = answerDtos;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

package com.resttest.dto;

import com.resttest.model.Answer;
import com.resttest.model.Test;

import java.util.List;

/**
 * Created by kvasa on 01.01.2017.
 */
public class QuestionDto {

    private Long id;
    private String question;
    private List<Answer> answers;

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

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}

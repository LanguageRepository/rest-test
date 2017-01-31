package com.resttest.dto;

import com.resttest.model.Paragraph;
import com.resttest.model.Question;

import java.util.List;

/**
 * Created by kvasa on 01.01.2017.
 */
public class TestDto {

    private Long id;
    private String name;
    private String owner;
    private Paragraph paragraph;
    private List<Question> questions;

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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Paragraph getParagraph() {
        return paragraph;
    }

    public void setParagraph(Paragraph paragraph) {
        this.paragraph = paragraph;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}

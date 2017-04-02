package com.resttest.model;

import javax.persistence.*;

/**
 * Created by Владислав on 01.04.2017.
 */
@Entity
@Table(name = "answer_represent")
public class AnswerRepresent {

    private Long id;

    private String answer;

    private String type;

    private Integer serialNumber;

    private QuestionRepresent question;

    private Boolean isChosen = false;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Column
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column
    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    @ManyToOne
    public QuestionRepresent getQuestion() {
        return question;
    }

    public void setQuestion(QuestionRepresent question) {
        this.question = question;
    }

    @Column
    public Boolean getChosen() {
        return isChosen;
    }

    public void setChosen(Boolean chosen) {
        isChosen = chosen;
    }
}

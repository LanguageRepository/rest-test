package com.resttest.model;

import javax.persistence.*;

/**
 * Created by Владислав on 26.03.2017.
 */
@Entity
@Table(name = "question_represent")
public class QuestionRepresent {

    private Long id;

    private Question question;

    private Integer serialNumber;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Column
    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }
}

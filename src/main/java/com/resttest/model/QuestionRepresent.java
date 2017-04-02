package com.resttest.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Владислав on 26.03.2017.
 */
@Entity
@Table(name = "question_represent")
public class QuestionRepresent {

    private Long id;

    private Question question;

    private Integer serialNumber;

    private Boolean isAnswered = false;

    private List<AnswerRepresent> answers;

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

    @Column
    public Boolean getAnswered() {
        return isAnswered;
    }

    public void setAnswered(Boolean answered) {
        isAnswered = answered;
    }

    @OneToMany(mappedBy = "question")
    public List<AnswerRepresent> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerRepresent> answers) {
        this.answers = answers;
    }
}

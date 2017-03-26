package com.resttest.model;

import javax.persistence.*;

/**
 * Created by Владислав on 26.03.2017.
 */
@Entity
@Table(name = "question_represent")
public class QuestionRepresent {

    private Long id;
    private Question presentQuestion;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne
    @PrimaryKeyJoinColumn
    public Question getPresentQuestion() {
        return presentQuestion;
    }

    public void setPresentQuestion(Question presentQuestion) {
        this.presentQuestion = presentQuestion;
    }
}

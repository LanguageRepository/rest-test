package com.resttest.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Владислав on 23.03.2017.
 */
@Entity
@Table(name = "test_passage")
public class TestPassage {

    private Long id;

    private String name;

    private Test test;

    private List<Question> questions;

    private User passingUser;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "test_passage_questions",
                joinColumns = @JoinColumn(name = "test_passage_id"),
                inverseJoinColumns = @JoinColumn(name = "question_id"))
    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @ManyToOne
    public User getPassingUser() {
        return passingUser;
    }

    public void setPassingUser(User passingUser) {
        this.passingUser = passingUser;
    }
}

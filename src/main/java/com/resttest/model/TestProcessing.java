package com.resttest.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Владислав on 27.03.2017.
 */
@Entity
@Table(name = "test_processing")
public class TestProcessing {

    private Long id;

    private Test test;

    private List<QuestionRepresent> questionsRepresent;

    private TestAccess testAccess;

    private User passingUser;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    @OneToMany(cascade = CascadeType.ALL)
    public List<QuestionRepresent> getQuestionsRepresent() {
        return questionsRepresent;
    }

    public void setQuestionsRepresent(List<QuestionRepresent> questionsRepresent) {
        this.questionsRepresent = questionsRepresent;
    }

    @ManyToOne
    public User getPassingUser() {
        return passingUser;
    }

    public void setPassingUser(User passingUser) {
        this.passingUser = passingUser;
    }

    @ManyToOne
    public TestAccess getTestAccess() {
        return testAccess;
    }

    public void setTestAccess(TestAccess testAccess) {
        this.testAccess = testAccess;
    }
}

package com.resttest.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "test_access")
public class TestAccess {

    private Long id;

    private Test test;

    private List<User> users;

    private Long initialTerm;

    private Long deadline;

    private String type;

    private Integer numberOfAttempts;

    private String timeToPass;

    private Boolean isActive = false;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "test_access_users",
               joinColumns = @JoinColumn(name = "test_access_id"),
               inverseJoinColumns = @JoinColumn(name = "user_id"))
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Column
    public Long getInitialTerm() {
        return initialTerm;
    }

    public void setInitialTerm(Long initialTerm) {
        this.initialTerm = initialTerm;
    }

    @Column
    public Long getDeadline() {
        return deadline;
    }

    public void setDeadline(Long deadline) {
        this.deadline = deadline;
    }

    @Column
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column
    public Integer getNumberOfAttempts() {
        return numberOfAttempts;
    }

    public void setNumberOfAttempts(Integer numberOfAttempts) {
        this.numberOfAttempts = numberOfAttempts;
    }

    @Column
    public String getTimeToPass() {
        return timeToPass;
    }

    public void setTimeToPass(String timeToPass) {
        this.timeToPass = timeToPass;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}

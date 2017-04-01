package com.resttest.dto.testaccess;

import com.resttest.dto.ShortView;

/**
 * Created by Владислав on 21.03.2017.
 */
public class TestAccessDtoForMainModule {

    private Long id;

    private String deadline;

    private String type;

    private String ownerName;

    private ShortView test;

    private Integer numberOfAttempts;

    private String initialTerm;

    private Boolean isActive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public ShortView getTest() {
        return test;
    }

    public void setTest(ShortView test) {
        this.test = test;
    }

    public Integer getNumberOfAttempts() {
        return numberOfAttempts;
    }

    public void setNumberOfAttempts(Integer numberOfAttempts) {
        this.numberOfAttempts = numberOfAttempts;
    }

    public String getInitialTerm() {
        return initialTerm;
    }

    public void setInitialTerm(String initialTerm) {
        this.initialTerm = initialTerm;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}

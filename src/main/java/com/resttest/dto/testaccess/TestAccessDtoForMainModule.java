package com.resttest.dto.testaccess;

/**
 * Created by Владислав on 21.03.2017.
 */
public class TestAccessDtoForMainModule {

    private Long id;

    private String deadline;

    private String type;

    private String ownerName;

    private String testName;

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

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
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

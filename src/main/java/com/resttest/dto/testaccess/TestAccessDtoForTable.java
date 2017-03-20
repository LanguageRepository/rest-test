package com.resttest.dto.testaccess;

import java.util.Set;

public class TestAccessDtoForTable {

    private Long id;

    private String testName;

    private Set<String> departments;

    private String testOwner;

    private String initialTerm;

    private String deadline;

    private String type;

    private Boolean isActive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Set<String> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<String> departments) {
        this.departments = departments;
    }

    public String getTestOwner() {
        return testOwner;
    }

    public void setTestOwner(String testOwner) {
        this.testOwner = testOwner;
    }

    public String getInitialTerm() {
        return initialTerm;
    }

    public void setInitialTerm(String initialTerm) {
        this.initialTerm = initialTerm;
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}

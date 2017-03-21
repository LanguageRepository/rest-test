package com.resttest.dto.testaccess;

import com.resttest.dto.ShortView;
import com.resttest.dto.test.TestDto;

import java.util.List;

public class TestAccessDto {

    private Long id;

    private TestDto testDto;

    private List<ShortView> users;

    private String initialTerm;

    private String deadline;

    private int numberOfAttempts;

    private String type;

    private String timeToPass;

    private Boolean isActive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TestDto getTestDto() {
        return testDto;
    }

    public void setTestDto(TestDto testDto) {
        this.testDto = testDto;
    }

    public List<ShortView> getUsers() {
        return users;
    }

    public void setUsers(List<ShortView> users) {
        this.users = users;
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

    public int getNumberOfAttempts() {
        return numberOfAttempts;
    }

    public void setNumberOfAttempts(int numberOfAttempts) {
        this.numberOfAttempts = numberOfAttempts;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

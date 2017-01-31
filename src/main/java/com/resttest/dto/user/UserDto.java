package com.resttest.dto.user;

/**
 * Created by kvasa on 01.01.2017.
 */
public class UserDto {

    private Long id;
    private String username;
    private String password;
    private String simpleRole;
    private String lastName;
    private String firstName;
    private String middleName;
    private String email;
    private String phone;
    private String description;
    private String department;
    private Long department_id;
    private Boolean notifyByMail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSimpleRole() {
        return simpleRole;
    }

    public void setSimpleRole(String simpleRole) {
        this.simpleRole = simpleRole;
    }

    public Long getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Long department_id) {
        this.department_id = department_id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getNotifyByMail() {
        return notifyByMail;
    }

    public void setNotifyByMail(Boolean notifyByMail) {
        this.notifyByMail = notifyByMail;
    }
}

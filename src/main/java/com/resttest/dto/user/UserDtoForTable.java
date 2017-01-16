package com.resttest.dto.user;

import com.resttest.model.UserRole;

import java.util.List;
import java.util.Set;

/**
 * Created by kvasa on 07.01.2017.
 */
public class UserDtoForTable {

    private Long id;
    private String fullName;
    private String department;
    private List<UserRole> roles;
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }
}

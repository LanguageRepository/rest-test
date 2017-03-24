package com.resttest.dto.user;

import com.resttest.model.RoleEnum;

/**
 * Created by Владислав on 24.03.2017.
 */
public class UserDtoForMainModule {

    private Long id;

    private String username;

    private String email;

    private String role;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

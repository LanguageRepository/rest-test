package com.resttest.dto;

import com.resttest.model.Department;
import com.resttest.model.User;

import java.util.List;

/**
 * Created by kvasa on 01.01.2017.
 */
public class DepartmentDto {

    private Long id;
    private String name;
    private String description;
    private Department parent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Department getParent() {
        return parent;
    }

    public void setParent(Department parent) {
        this.parent = parent;
    }
}

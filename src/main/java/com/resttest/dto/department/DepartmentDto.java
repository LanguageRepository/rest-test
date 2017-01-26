package com.resttest.dto.department;

import java.util.List;

/**
 * Created by kvasa on 01.01.2017.
 */
public class DepartmentDto {

    private Long id;
    private String name;
    private String description;
    private List<DepartmentDto> children;
    private String type;
    private Long parent_id;

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

    public List<DepartmentDto> getChildren() {
        return children;
    }

    public void setChildren(List<DepartmentDto> children) {
        this.children = children;
    }

    public Long getParent_id() {
        return parent_id;
    }

    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

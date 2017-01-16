package com.resttest.dto.department;

import java.util.List;

/**
 * Created by kvasa on 01.01.2017.
 */
public class DepartmentDto {

    private Long id;
    private String name;
    private String description;
    private DepartmentDto parent;
    private List<ShortViewForDepartment> childs;
    private String type;

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

    public DepartmentDto getParent() {
        return parent;
    }

    public void setParent(DepartmentDto parent) {
        this.parent = parent;
    }

    public List<ShortViewForDepartment> getChilds() {
        return childs;
    }

    public void setChilds(List<ShortViewForDepartment> childs) {
        this.childs = childs;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

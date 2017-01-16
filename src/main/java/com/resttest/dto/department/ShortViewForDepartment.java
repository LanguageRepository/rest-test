package com.resttest.dto.department;

import java.util.List;

/**
 * Created by kvasa on 15.01.2017.
 */
public class ShortViewForDepartment {

    private Long id;
    private String name;
    private String type;
    private List<ShortViewForDepartment> childs;

    public List<ShortViewForDepartment> getChilds() {
        return childs;
    }

    public void setChilds(List<ShortViewForDepartment> childs) {
        this.childs = childs;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

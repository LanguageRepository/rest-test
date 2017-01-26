package com.resttest.dto.department;

import java.util.List;

/**
 * Created by kvasa on 15.01.2017.
 */
public class ShortViewForDepartment {

    private Long id;
    private String text;
    private List<ShortViewForDepartment> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ShortViewForDepartment> getChildren() {
        return children;
    }

    public void setChildren(List<ShortViewForDepartment> children) {
        this.children = children;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

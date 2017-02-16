package com.resttest.dto.paragraph;

import com.resttest.dto.ShortView;
import com.resttest.model.Paragraph;

import java.util.List;

/**
 * Created by kvasa on 01.01.2017.
 */
public class ParagraphDto {

    private Long id;
    private String name;
    private Paragraph parent;
    private List<ShortView> tests;
    private List<ShortView> childs;

    public List<ShortView> getTests() {
        return tests;
    }

    public void setTests(List<ShortView> tests) {
        this.tests = tests;
    }

    public List<ShortView> getChilds() {
        return childs;
    }

    public void setChilds(List<ShortView> childs) {
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

    public Paragraph getParent() {
        return parent;
    }

    public void setParent(Paragraph parent) {
        this.parent = parent;
    }
}

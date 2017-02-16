package com.resttest.dto.paragraph;

import java.util.List;

public class ParagraphDtoForTree {

    private Long id;
    private String text;
    private List<ParagraphDtoForTree> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<ParagraphDtoForTree> getChildren() {
        return children;
    }

    public void setChildren(List<ParagraphDtoForTree> children) {
        this.children = children;
    }
}

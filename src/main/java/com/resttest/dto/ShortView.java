package com.resttest.dto;

/**
 * Created by kvasa on 02.01.2017.
 */
public class ShortView {

    private Long id;
    private String name;

    public ShortView() {
    }

    public ShortView(Long id, String name) {
        this.id = id;
        this.name = name;
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
}

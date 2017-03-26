package com.resttest.dto.test;

/**
 * Created by Владислав on 22.03.2017.
 */
public class TestDtoForMainModule {

    private Long id;
    private Boolean isDeleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}

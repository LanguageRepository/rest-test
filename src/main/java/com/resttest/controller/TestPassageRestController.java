package com.resttest.controller;

import com.resttest.dto.testpassage.TestPassageDto;
import com.resttest.service.TestPassageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Владислав on 23.03.2017.
 */
@RestController
@RequestMapping(value = "/testpassage")
public class TestPassageRestController {

    @Autowired
    private TestPassageService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public TestPassageDto getTestPassage(@PathVariable(value = "id") Long id) {
        return service.getTestPassage(id);
    }

}

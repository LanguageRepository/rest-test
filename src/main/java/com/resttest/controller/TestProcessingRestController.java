package com.resttest.controller;

import com.resttest.dto.testprocessing.TestProcessingDto;
import com.resttest.service.TestProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Владислав on 28.03.2017.
 */
@RestController
@RequestMapping("/processing/")
public class TestProcessingRestController {

    @Autowired
    private TestProcessingService testProcessingService;

    @RequestMapping(value = "/{testId}/{testAccessId}", method = RequestMethod.GET, produces = "application/json")
    public TestProcessingDto getTestProcessingByTest(@PathVariable("testId") Long testId,
                                                     @PathVariable("testAccessId") Long taskAccessId) {
        return testProcessingService.getTestProcessingByTestId(testId, taskAccessId);
    }

}

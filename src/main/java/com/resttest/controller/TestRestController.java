package com.resttest.controller;

import com.resttest.dto.test.TestDto;
import com.resttest.dto.test.TestDtoForTable;
import com.resttest.model.Test;
import com.resttest.service.AnswerService;
import com.resttest.service.QuestionService;
import com.resttest.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestRestController {

    @Autowired
    private TestService testService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public TestDto getTest(@PathVariable("id") Long id) {
        return testService.getTest(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public TestDtoForTable createTest(@RequestBody TestDto dto) {
        return testService.createTest(dto);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateTest(@RequestBody TestDto dto) {
        testService.updateTest(dto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteTest(@PathVariable("id")Long id) {
        testService.deleteTest(id);
    }

    @RequestMapping(value = "/table", method = RequestMethod.GET, produces = "application/json")
    public List<TestDtoForTable> getAllTestForTable() {
        return testService.getAllTestsForTable();
    }

    @RequestMapping(value = "/table/{id}", method = RequestMethod.GET, produces = "application/json")
    public List<TestDtoForTable> getTestsByParagraph(@PathVariable("id") Long id) {
        return testService.getTestsByParagraph(id);
    }

    @RequestMapping(value = "/{id}/processed", method = RequestMethod.GET, produces = "application/json")
    public TestDto getTestForThePass(@PathVariable Long id) {
        return testService.getTestForThePass(id);
    }

}

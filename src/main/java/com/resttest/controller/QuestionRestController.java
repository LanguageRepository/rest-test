package com.resttest.controller;

import com.resttest.dto.QuestionDto;
import com.resttest.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question")
public class QuestionRestController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public QuestionDto getQuestion(@PathVariable("id") long id) {
        return questionService.getQuestion(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createQuestion(@RequestBody QuestionDto dto) {
        questionService.createQuestion(dto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateQuestion(@PathVariable("id") long id, @RequestBody QuestionDto dto) {
        dto.setId(id);
        questionService.updateQuestion(dto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public void deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
    }

}

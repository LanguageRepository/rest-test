package com.resttest.controller;

import com.resttest.dto.ShortView;
import com.resttest.dto.question.QuestionDto;
import com.resttest.dto.question.QuestionDtoForTable;
import com.resttest.repository.QuestionJpaRepository;
import com.resttest.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionRestController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionJpaRepository jpaRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public QuestionDto getQuestion(@PathVariable("id") long id) {
        return questionService.getQuestion(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createQuestion(@RequestBody QuestionDto dto) {
        questionService.createQuestion(dto);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String updateQuestion(@RequestBody QuestionDto dto) {
        if(!jpaRepository.exists(dto.getId())) {
            createQuestion(dto);
        } else {
            questionService.updateQuestion(dto);
        }
        return "ok";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public void deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
    }

    @RequestMapping(value = "/test/{id}", method = RequestMethod.GET, produces = "application/json")
    public List<QuestionDtoForTable> getQuestionsByTestId(@PathVariable Long id) {
        return questionService.getQuestionsForTable(id);
    }

    @RequestMapping(value = "/test/name/{id}", method = RequestMethod.GET, produces = "application/json")
    public ShortView getTestNameById(@PathVariable Long id) {
        return questionService.getCurrentTestName(id);
    }

    @RequestMapping(value = "/new/{questionId}", method = RequestMethod.GET, produces = "application/json")
    public List<Long> getIdsForAnswers(@PathVariable Long questionId) {
        return questionService.getIdForAnswers(questionId);
    }

}

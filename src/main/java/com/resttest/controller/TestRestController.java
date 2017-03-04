package com.resttest.controller;

import com.resttest.dto.AnswerDto;
import com.resttest.dto.question.QuestionDto;
import com.resttest.dto.test.TestDto;
import com.resttest.dto.test.TestDtoForTable;
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
        List<QuestionDto> currentQuestion = testService.getTest(id).getQuestions();
        for (QuestionDto aCurrentQuestion : currentQuestion) {
            List<AnswerDto> answers = aCurrentQuestion.getAnswers();
            for (AnswerDto answer : answers) {
                answerService.deleteAnswer(answer.getId());
            }
            questionService.deleteQuestion(aCurrentQuestion.getId());
        }
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

}

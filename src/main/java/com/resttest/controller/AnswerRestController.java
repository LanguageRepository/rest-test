package com.resttest.controller;

import com.resttest.dto.AnswerDto;
import com.resttest.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answer")
public class AnswerRestController {

    @Autowired
    private AnswerService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public AnswerDto getAnswer(@PathVariable("id") Long id) {
        return service.getAnswer(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createAnswer(@RequestBody AnswerDto dto) {
        service.createAnswer(dto);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateAnswer(@RequestBody AnswerDto dto) {
        service.updateAnswer(dto);
    }

    @RequestMapping(value = "/id", method = RequestMethod.DELETE, produces = "application/json")
    public void deleteAnswer(@PathVariable Long id) {
        service.deleteAnswer(id);
    }

}

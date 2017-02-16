package com.resttest.utils;

import com.resttest.dto.QuestionDto;
import com.resttest.model.Question;
import com.resttest.repository.QuestionJpaRepository;
import com.resttest.repository.TestJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionUtils {

    @Autowired
    private AnswerUtils answerUtils;

    @Autowired
    private TestJpaRepository testJpaRepository;

    @Autowired
    private QuestionJpaRepository jpaRepository;

    public QuestionDto convertEntityToDto(Question entity) {
        QuestionDto dto = new QuestionDto();
        dto.setId(entity.getId());
        dto.setTestId(entity.getTest().getId());
        dto.setAnswers(answerUtils.convertEntitiesToDtos(entity.getAnswers()));
        dto.setQuestion(entity.getQuestion());
        return dto;
    }

    public Question convertDtoToEntity(QuestionDto dto) {
        Question entity = new Question();
        entity.setId(dto.getId());
        entity.setQuestion(dto.getQuestion());
        entity.setAnswers(answerUtils.convertDtosToEntities(dto.getAnswers()));
        entity.setTest(testJpaRepository.getOne(dto.getTestId()));
        return entity;
    }

    public List<QuestionDto> convertEntitiesToDtos(List<Question> entities) {
        List<QuestionDto> dtos = new ArrayList<>();
        for (Question entity : entities) {
            QuestionDto dto = new QuestionDto();
            dto.setId(entity.getId());
            dto.setQuestion(entity.getQuestion());
            dto.setAnswers(answerUtils.convertEntitiesToDtos(entity.getAnswers()));
            dto.setTestId(entity.getTest().getId());
            dtos.add(dto);
        }
        return dtos;
    }

    public List<Question> convertDtosToEntities(List<QuestionDto> dtos) {
        List<Question> entities = new ArrayList<>();
        for (QuestionDto dto : dtos) {
            Question entity = new Question();
            entity.setId(dto.getId());
            entity.setTest(testJpaRepository.getOne(dto.getTestId()));
            entity.setAnswers(answerUtils.convertDtosToEntities(dto.getAnswers()));
            entity.setQuestion(dto.getQuestion());
            entities.add(entity);
        }
        return entities;
    }

}

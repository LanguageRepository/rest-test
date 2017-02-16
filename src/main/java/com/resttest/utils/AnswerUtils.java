package com.resttest.utils;

import com.resttest.dto.AnswerDto;
import com.resttest.dto.ShortView;
import com.resttest.model.Answer;
import com.resttest.repository.AnswerJpaRepository;
import com.resttest.repository.QuestionJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AnswerUtils {

    @Autowired
    private AnswerJpaRepository jpaRepository;

    @Autowired
    private QuestionJpaRepository questionJpaRepository;

    public AnswerDto convertEntityToDto(Answer entity) {
        AnswerDto dto = new AnswerDto();
        dto.setId(entity.getId());
        dto.setAnswer(entity.getAnswer());
        dto.setQuestionId(entity.getQuestion().getId());
        dto.setRightValue(entity.getRightValue());
        return dto;
    }

    public Answer convertDtoToEntity(AnswerDto dto) {
        Answer answer = new Answer();
        answer.setId(dto.getId());
        answer.setRightValue(dto.getRightValue());
        answer.setAnswer(dto.getAnswer());
        answer.setQuestion(questionJpaRepository.getOne(dto.getQuestionId()));
        return answer;
    }

    public List<AnswerDto> convertEntitiesToDtos(List<Answer> entities) {
        List<AnswerDto> dtos = new ArrayList<>();
        for (Answer entity : entities) {
            AnswerDto dto = new AnswerDto();
            dto.setId(entity.getId());
            dto.setRightValue(entity.getRightValue());
            dto.setQuestionId(entity.getQuestion().getId());
            dto.setAnswer(entity.getAnswer());
            dtos.add(dto);
        }
        return dtos;
    }

    public List<Answer> convertDtosToEntities(List<AnswerDto> dtos) {
        List<Answer> entities = new ArrayList<>();
        for (AnswerDto dto : dtos) {
            Answer answer = new Answer();
            answer.setId(dto.getId());
            answer.setQuestion(questionJpaRepository.getOne(dto.getQuestionId()));
            answer.setAnswer(dto.getAnswer());
            answer.setRightValue(dto.getRightValue());
            entities.add(answer);
        }
        return entities;
    }

    public ShortView convertEntityToShortView(Answer answer) {
        ShortView shortView = new ShortView();
        shortView.setId(answer.getId());
        shortView.setName(answer.getAnswer());
        return shortView;
    }

}

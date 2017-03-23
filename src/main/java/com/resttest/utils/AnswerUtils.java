package com.resttest.utils;

import com.resttest.dto.answer.AnswerDto;
import com.resttest.dto.ShortView;
import com.resttest.model.Answer;
import com.resttest.model.AnswerType;
import com.resttest.repository.AnswerJpaRepository;
import com.resttest.repository.QuestionJpaRepository;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        dto.setType(entity.getType().toString());
        return dto;
    }

    public Answer convertDtoToEntity(AnswerDto dto) {
        Answer answer = new Answer();
        answer.setId(dto.getId());
        answer.setRightValue(dto.getRightValue());
        answer.setAnswer(dto.getAnswer());
        answer.setQuestion(questionJpaRepository.getOne(dto.getQuestionId()));
        answer.setType(checkTypes(dto));
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
            dto.setType(entity.getType().toString());
            dtos.add(dto);
        }
        return dtos;
    }

    public List<AnswerDto> convertEntitiesToDtosForPut(List<Answer> entities) {
        List<AnswerDto> dtos = new ArrayList<>();
        for (Answer entity : entities) {
            AnswerDto dto = new AnswerDto();
            dto.setId(entity.getId());
            dto.setRightValue(entity.getRightValue());
            dto.setAnswer(entity.getAnswer());
            dto.setType(entity.getType().toString());
            dtos.add(dto);
        }
        return dtos;
    }

    public List<Answer> convertDtosToEntities(List<AnswerDto> dtos) {
        List<Answer> entities = new ArrayList<>();
        for (AnswerDto dto : dtos) {
            Answer answer = new Answer();
            if(dto.getId() != null) {
                answer.setId(dto.getId());
                answer.setQuestion(questionJpaRepository.getOne(dto.getQuestionId()));
            }
            answer.setAnswer(dto.getAnswer());
            answer.setRightValue(dto.getRightValue());
            if(dtos.size() > 1) {
                answer.setType(checkTypes(dto));
            } else {
                answer.setType(AnswerType.ANSWER_TYPE_STRING);
            }
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

    private AnswerType checkTypes(AnswerDto dto) {
        AnswerType type = AnswerType.ANSWER_TYPE_BOOL;
        if(Objects.equals(dto.getType(), "ANSWER_TYPE_BOOL")) {
            type = AnswerType.ANSWER_TYPE_BOOL;
        } else if(Objects.equals(dto.getType(), "ANSWER_TYPE_STRING")) {
            type = AnswerType.ANSWER_TYPE_STRING;
        }
        return type;
    }

    public List<AnswerDto> convertEntitiesToDtosWithMarkdownPreprocessor(List<Answer> entities) {
        List<AnswerDto> dtos = new ArrayList<>();
        Parser parser = Parser.builder().build();
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        entities.forEach(s -> {
            AnswerDto dto = new AnswerDto();
            Node node = parser.parse(s.getAnswer());
            dto.setId(s.getId());
            dto.setQuestionId(s.getQuestion().getId());
            dto.setType(s.getType().toString());
            dto.setRightValue(s.getRightValue());
            dto.setAnswer(renderer.render(node));
            dtos.add(dto);
        });
        return dtos;
    }

}

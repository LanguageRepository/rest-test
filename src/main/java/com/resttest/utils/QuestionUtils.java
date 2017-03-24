package com.resttest.utils;

import com.resttest.dto.question.QuestionDto;
import com.resttest.dto.question.QuestionDtoForMainModule;
import com.resttest.dto.question.QuestionDtoForTable;
import com.resttest.model.Question;
import com.resttest.model.QuestionAnswerType;
import com.resttest.repository.QuestionJpaRepository;
import com.resttest.repository.TestJpaRepository;
import org.commonmark.node.Node;
import org.commonmark.node.Visitor;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        dto.setType(checkTypeToConvertEntityToDto(entity));
        return dto;
    }

    public QuestionDto convertEntityToDtoForPut(Question entity) {
        QuestionDto dto = new QuestionDto();
        dto.setId(entity.getId());
        dto.setTestId(entity.getTest().getId());
        dto.setAnswers(answerUtils.convertEntitiesToDtosForPut(entity.getAnswers()));
        dto.setQuestion(entity.getQuestion());
        dto.setType(checkTypeToConvertEntityToDto(entity));
        return dto;
    }

    public Question convertDtoToEntity(QuestionDto dto) {
        Question entity = new Question();
        entity.setId(dto.getId());
        entity.setQuestion(dto.getQuestion());
        entity.setAnswers(answerUtils.convertDtosToEntities(dto.getAnswers()));
        entity.setTest(testJpaRepository.getOne(dto.getTestId()));
        if(dto.getAnswers().size() > 1) {
            entity.setType(valueOfRightValues(dto) == 1 ? QuestionAnswerType.ONE_RIGHT_VALUE : QuestionAnswerType.MANY_RIGHT_VALUE);
        } else {
            entity.setType(QuestionAnswerType.MANUAL_INPUT);
        }
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
            dto.setType(checkTypeToConvertEntityToDto(entity));
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

    public List<QuestionDtoForTable> convertEntitiesToDtosForTable(List<Question> entities) {
        List<QuestionDtoForTable> dtos = new ArrayList<>();
        for (Question entity1 : entities) {
            QuestionDtoForTable entity = new QuestionDtoForTable();
            entity.setId(entity1.getId());
            entity.setName(entity1.getQuestion());
            entity.setTestId(entity1.getTest().getId());
            entity.setTestName(entity1.getTest().getName());
            dtos.add(entity);
        }
        return dtos;
    }

    private String checkTypeToConvertEntityToDto(Question entity) {
        String type;
        if(entity.getType().equals(QuestionAnswerType.MANUAL_INPUT)) {
            type = "manual_input";
        } else if(entity.getType().equals(QuestionAnswerType.MANY_RIGHT_VALUE)) {
            type = "many_right";
        } else {
            type = "one_right";
        }
        return type;
    }

    public int valueOfRightValues(QuestionDto dto) {
        int temp = 0;
        for (int i = 0; i < dto.getAnswers().size(); i++) {
            if(Objects.equals(dto.getAnswers().get(i).getRightValue(), "true")) {
                temp++;
            }
        }
        return temp;
    }

    public List<QuestionDto> convertEntitiesToDtosWithMarkdownPreprocessor(List<Question> entities) {
        List<QuestionDto> dtos = new ArrayList<>();
        Parser parser = Parser.builder().build();
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        entities.forEach(s -> {
            Node node = parser.parse(s.getQuestion());
            QuestionDto dto = new QuestionDto();
            dto.setId(s.getId());
            dto.setType(s.getType().toString());
            dto.setTestId(s.getTest().getId());
            dto.setAnswers(answerUtils.convertEntitiesToDtosWithMarkdownPreprocessor(s.getAnswers()));
            dto.setQuestion(renderer.render(node));
            dtos.add(dto);
        });
        return dtos;
    }

    public List<QuestionDtoForMainModule> convertEntitiesToDtosForMainModule(List<Question> entities) {
        List<QuestionDtoForMainModule> dtos = new ArrayList<>();
        Parser parser = Parser.builder().build();
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        final Long[] i = {0l};
        entities.forEach(s -> {
            QuestionDtoForMainModule dto = new QuestionDtoForMainModule();
            Node node = parser.parse(s.getQuestion());
            dto.setId(i[0]++);
            dto.setAnswerDtos(answerUtils.convertEntitiesToDtosForMainModule(s.getAnswers()));
            dto.setQuestion(renderer.render(node));
            dto.setType(s.getType().toString());
            dtos.add(dto);
        });
        return dtos;
    }

}

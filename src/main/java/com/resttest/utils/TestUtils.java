package com.resttest.utils;

import com.resttest.dto.test.TestDto;
import com.resttest.dto.test.TestDtoForTable;
import com.resttest.model.Test;
import com.resttest.repository.ParagraphJpaRepository;
import com.resttest.repository.TestJpaRepository;
import com.resttest.repository.UserJpaRepository;
import com.resttest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TestUtils {

    @Autowired
    private TestJpaRepository jpaRepository;

    @Autowired
    private UserUtils userUtils;

    @Autowired
    private ParagraphJpaRepository paragraphJpaRepository;

    @Autowired
    private QuestionUtils questionUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private UserJpaRepository userJpaRepository;

    public TestDto convertEntityToDto(Test entity) {
        TestDto dto = new TestDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setOwnerName(entity.getOwner().getUsername());
        dto.setParagraphId(entity.getParagraph().getId());
        dto.setQuestions(questionUtils.convertEntitiesToDtos(entity.getQuestions()));
        dto.setDescription(entity.getDescription());
        dto.setDeleted(entity.getDeleted());
        return dto;
    }

    public Test convertDtoToEntity(TestDto dto) {
        Test entity = new Test();
        if(dto.getId() != null) {
            entity.setId(dto.getId());
        }
        entity.setName(dto.getName());
        entity.setOwner(userJpaRepository.getUserByUsername(dto.getOwnerName()));
        entity.setParagraph(paragraphJpaRepository.getOne(dto.getParagraphId()));
        entity.setDescription(dto.getDescription());
        entity.setDeleted(dto.getDeleted());
        return entity;
    }

    public List<TestDtoForTable> convertEntitiesToDtosForTable(List<Test> entities) {
        List<TestDtoForTable> dtos = new ArrayList<>();
        for (Test entity : entities) {
            TestDtoForTable dto = new TestDtoForTable();
            dto.setId(entity.getId());
            dto.setOwnerName(entity.getOwner().getUsername());
            dto.setName(entity.getName());
            dto.setDescription(entity.getDescription());
            dto.setDeleted(entity.getDeleted());
            dtos.add(dto);
        }
        return dtos;
    }

    public TestDtoForTable convertEntityToShortDto(Test entity) {
        TestDtoForTable dto = new TestDtoForTable();
        dto.setId(entity.getId());
        dto.setOwnerName(entity.getOwner().getUsername());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setDeleted(entity.getDeleted());
        return dto;
    }

    public TestDto convertEntityToDtoWithMarkdownPreprocessor(Test entity) {
        TestDto dto = new TestDto();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setName(entity.getName());
        dto.setQuestions(questionUtils.convertEntitiesToDtosWithMarkdownPreprocessor(entity.getQuestions()));
        dto.setDeleted(entity.getDeleted());
        return dto;
    }

}

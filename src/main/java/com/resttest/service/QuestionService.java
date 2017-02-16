package com.resttest.service;

import com.resttest.dto.QuestionDto;
import com.resttest.repository.QuestionJpaRepository;
import com.resttest.utils.QuestionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QuestionService {

    @Autowired
    private QuestionUtils questionUtils;

    @Autowired
    private QuestionJpaRepository jpaRepository;

    @Transactional
    public QuestionDto getQuestion(Long id) {
        return questionUtils.convertEntityToDto(jpaRepository.getOne(id));
    }

    @Transactional
    public void createQuestion(QuestionDto dto) {
        jpaRepository.save(questionUtils.convertDtoToEntity(dto));
    }

    @Transactional
    public void updateQuestion(QuestionDto dto) {
        jpaRepository.save(questionUtils.convertDtoToEntity(dto));
    }

    @Transactional
    public void deleteQuestion(Long id) {
        jpaRepository.delete(id);
    }

}

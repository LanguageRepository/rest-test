package com.resttest.service;

import com.resttest.dto.answer.AnswerDto;
import com.resttest.repository.AnswerJpaRepository;
import com.resttest.utils.AnswerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AnswerService {

    @Autowired
    private AnswerUtils answerUtils;

    @Autowired
    private AnswerJpaRepository answerJpaRepository;

    @Transactional
    public AnswerDto getAnswer(Long id) {
        return answerUtils.convertEntityToDto(answerJpaRepository.getOne(id));
    }

    @Transactional
    public void createAnswer(AnswerDto dto) {
        answerJpaRepository.save(answerUtils.convertDtoToEntity(dto));
    }

    @Transactional
    public void updateAnswer(AnswerDto dto) {
        answerJpaRepository.save(answerUtils.convertDtoToEntity(dto));
    }

    @Transactional
    public void deleteAnswer(Long id) {
        answerJpaRepository.delete(id);
    }

}

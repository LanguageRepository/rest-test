package com.resttest.service;

import com.resttest.dto.ShortView;
import com.resttest.dto.question.QuestionDto;
import com.resttest.dto.question.QuestionDtoForTable;
import com.resttest.model.Question;
import com.resttest.repository.AnswerJpaRepository;
import com.resttest.repository.QuestionJpaRepository;
import com.resttest.repository.TestJpaRepository;
import com.resttest.utils.AnswerUtils;
import com.resttest.utils.QuestionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class QuestionService {

    @Autowired
    private QuestionUtils questionUtils;

    @Autowired
    private QuestionJpaRepository jpaRepository;

    @Autowired
    private TestJpaRepository testJpaRepository;

    @Autowired
    private AnswerJpaRepository answerJpaRepository;

    @Autowired
    private AnswerUtils answerUtils;

    @Transactional
    public QuestionDto getQuestion(Long id) {
        return questionUtils.convertEntityToDto(jpaRepository.getOne(id));
    }

    @Transactional
    public void createQuestion(QuestionDto dto) {
        answerJpaRepository.save(answerUtils.convertDtosToEntities(dto.getAnswers()));
        jpaRepository.save(questionUtils.convertDtoToEntity(dto));
    }

    @Transactional
    public void updateQuestion(QuestionDto dto) {
        Question currentQuestion = jpaRepository.getOne(dto.getId());
        for (int i = 0; i < currentQuestion.getAnswers().size(); i++) {
            answerJpaRepository.delete(currentQuestion.getAnswers().get(i).getId());
        }
        answerJpaRepository.save(answerUtils.convertDtosToEntities(dto.getAnswers()));
        jpaRepository.save(questionUtils.convertDtoToEntity(dto));
    }

    @Transactional
    public void deleteQuestion(Long id) {
        jpaRepository.delete(id);
    }

    @Transactional
    public List<QuestionDtoForTable> getQuestionsForTable(Long id) {
        return questionUtils.convertEntitiesToDtosForTable(jpaRepository.findQuestionByTestId(id));
    }

    @Transactional
    public ShortView getCurrentTestName(Long id) {
        ShortView shortView = new ShortView();
        shortView.setId(testJpaRepository.findOne(id).getId());
        shortView.setName(testJpaRepository.findOne(id).getName());
        return shortView;
    }

    @Transactional
    public List<Long> getIdForAnswers(Long questionId) {
        List<Long> answersIds = new ArrayList<>();
        Question question = jpaRepository.getOne(questionId);
        for (int i = 0; i < question.getAnswers().size(); i++) {
            answersIds.add(question.getAnswers().get(i).getId());
        }
        return answersIds;
    }

}

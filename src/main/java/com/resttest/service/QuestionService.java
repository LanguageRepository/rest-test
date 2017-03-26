package com.resttest.service;

import com.resttest.dto.answer.AnswerDto;
import com.resttest.dto.ShortView;
import com.resttest.dto.question.QuestionDto;
import com.resttest.dto.question.QuestionDtoForTable;
import com.resttest.model.Answer;
import com.resttest.model.Question;
import com.resttest.model.QuestionAnswerType;
import com.resttest.repository.AnswerJpaRepository;
import com.resttest.repository.QuestionJpaRepository;
import com.resttest.repository.TestJpaRepository;
import com.resttest.utils.AnswerUtils;
import com.resttest.utils.QuestionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    private AnswerService answerService;

    @Transactional
    public QuestionDto getQuestion(Long id) {
        Question question = jpaRepository.getOne(id);
        question.setAnswers(question.getAnswers().stream()
                .sorted(((o1, o2) -> o1.getId().compareTo(o2.getId()))).collect(Collectors.toList()));
        return questionUtils.convertEntityToDto(jpaRepository.getOne(id));
    }

    @Transactional
    public QuestionDto createQuestion(QuestionDto dto) {
        Question currentQuestion = jpaRepository.save(questionUtils.convertDtoToEntity(dto));
        for (int i = 0; i < currentQuestion.getAnswers().size(); i++) {
            currentQuestion.getAnswers().get(i).setQuestion(currentQuestion);
            answerJpaRepository.save(currentQuestion.getAnswers().get(i));
        }
        return questionUtils.convertEntityToDto(jpaRepository.getOne(currentQuestion.getId()));
    }

    @Transactional
    public QuestionDto updateQuestion(QuestionDto dto) {
        Question currentQuestion = jpaRepository.getOne(dto.getId());
        currentQuestion.setQuestion(dto.getQuestion());
        currentQuestion.setTest(testJpaRepository.getOne(dto.getTestId()));
        if(dto.getAnswers().size() >= 1) {
            currentQuestion.setType(questionUtils.valueOfRightValues(dto) == 1 ?
                    QuestionAnswerType.ONE_RIGHT_VALUE : QuestionAnswerType.MANY_RIGHT_VALUE);
        } else {
            currentQuestion.setType(QuestionAnswerType.MANUAL_INPUT);
        }
        updateAnswers(currentQuestion, dto);
        jpaRepository.save(currentQuestion);
        currentQuestion.setAnswers(currentQuestion.getAnswers().stream()
                .sorted((o1, o2) -> o1.getId().compareTo(o2.getId())).collect(Collectors.toList()));
        return questionUtils.convertEntityToDto(currentQuestion);
    }

    private void updateAnswers(Question entity, QuestionDto dto) {
        Set<Answer> entities = new HashSet<>(entity.getAnswers());
        Set<AnswerDto> dtos = new HashSet<>(dto.getAnswers());
        entities.removeAll(answerUtils.convertDtosToEntities(new ArrayList<>(dtos)));
        entities.forEach(s -> answerJpaRepository.delete(s.getId()));
        dto.getAnswers().forEach(s -> answerJpaRepository.save(answerUtils.convertDtoToEntity(s)));
    }

    @Transactional
    public void deleteQuestion(Long id) {
        Question entity = jpaRepository.getOne(id);
        entity.setDeleted(true);
    }

    @Transactional
    public List<QuestionDtoForTable> getQuestionsForTable(Long id) {
        List<Question> questions = jpaRepository.findQuestionByTestId(id);
        List<Question> presentQuestions = new ArrayList<>();
        for (int i = 0; i < questions.size(); i++) {
            if(questions.get(i).getDeleted() == false) {
                presentQuestions.add(questions.get(i));
            }
        }
        return questionUtils.convertEntitiesToDtosForTable(presentQuestions);
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

    private Question answersFilter(QuestionDto dto) {
        Question compared = jpaRepository.getOne(dto.getId());
        List<AnswerDto> comparingAnswers = dto.getAnswers();
        for (int i = 0; i < compared.getAnswers().size(); i++) {
            for (AnswerDto comparingAnswer : comparingAnswers) {
                if (comparingAnswer != null) {
                    if (Objects.equals(compared.getAnswers().get(i).getId(), comparingAnswers.get(i).getId())) {
                        compared.getAnswers().remove(i);
                    }
                }
            }
        }
        return compared;
    }

}

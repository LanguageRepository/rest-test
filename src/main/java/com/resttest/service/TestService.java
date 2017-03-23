package com.resttest.service;

import com.resttest.dto.test.TestDto;
import com.resttest.dto.test.TestDtoForTable;
import com.resttest.model.Question;
import com.resttest.model.Test;
import com.resttest.repository.AnswerJpaRepository;
import com.resttest.repository.QuestionJpaRepository;
import com.resttest.repository.TestJpaRepository;
import com.resttest.utils.TestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TestService {

    @Autowired
    private TestUtils testUtils;

    @Autowired
    private TestJpaRepository testJpaRepository;

    @Autowired
    private AnswerJpaRepository answerJpaRepository;

    @Autowired
    private QuestionJpaRepository questionJpaRepository;

    @Transactional
    public TestDto getTest(Long id) {
        return testUtils.convertEntityToDto(testJpaRepository.getOne(id));
    }

    @Transactional
    public TestDtoForTable createTest(TestDto dto) {
        return testUtils.convertEntityToShortDto(testJpaRepository.save(testUtils.convertDtoToEntity(dto)));
    }

    @Transactional
    public void updateTest(TestDto dto) {
        testJpaRepository.save(testUtils.convertDtoToEntity(dto));
    }

    @Transactional
    public void deleteTest(Long id) {
        List<Question> questions = testJpaRepository.getOne(id).getQuestions();
        for (int i = 0; i < questions.size(); i++) {
            for (int j = 0; j < questions.get(i).getAnswers().size(); j++) {
                answerJpaRepository.delete(questions.get(i).getAnswers().get(j).getId());
            }
            questionJpaRepository.delete(questions.get(i).getId());
        }
        testJpaRepository.delete(id);
    }

    @Transactional
    public List<TestDtoForTable> getAllTestsForTable() {
        return testUtils.convertEntitiesToDtosForTable(testJpaRepository.findAll());
    }

    @Transactional
    public List<TestDtoForTable> getTestsByParagraph(Long id) {
        return testUtils.convertEntitiesToDtosForTable(testJpaRepository.findTestsByParagraphId(id));
    }

    @Transactional
    public TestDto getTestForThePass(Long id) {
        Test entity = testJpaRepository.getOne(id);
        return testUtils.convertEntityToDtoWithMarkdownPreprocessor(entity);
    }

}

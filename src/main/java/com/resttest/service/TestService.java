package com.resttest.service;

import com.resttest.dto.test.TestDto;
import com.resttest.dto.test.TestDtoForTable;
import com.resttest.model.Answer;
import com.resttest.model.Paragraph;
import com.resttest.model.Question;
import com.resttest.model.Test;
import com.resttest.repository.AnswerJpaRepository;
import com.resttest.repository.ParagraphJpaRepository;
import com.resttest.repository.QuestionJpaRepository;
import com.resttest.repository.TestJpaRepository;
import com.resttest.utils.TestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Autowired
    private ParagraphJpaRepository paragraphJpaRepository;
    
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
        List<Question> questions = questionJpaRepository.findQuestionByTestId(id);
        for (int i = 0; i < questions.size(); i++) {
            questions.get(i).setDeleted(true);
        }
        testJpaRepository.getOne(id).setDeleted(true);
    }

    @Transactional
    public List<TestDtoForTable> getAllTestsForTable() {
        List<Test> result = new ArrayList<>();
        List<Test> dtos = testJpaRepository.findAll();
        for (int i = 0; i < dtos.size(); i++) {
            if(dtos.get(i).getDeleted() != true) {
                result.add(dtos.get(i));
            }
        }
        return testUtils.convertEntitiesToDtosForTable(result);
    }

    @Transactional
    public List<TestDtoForTable> getTestsByParagraph(Long id) {
        List<Test> result = new ArrayList<>();
        Paragraph entity = paragraphJpaRepository.getOne(id);
        if(entity.getChilds() != null || entity.getChilds().size() >= 1) {
            result = findTestsByParagraph(entity);
        }
        return testUtils.convertEntitiesToDtosForTable(result);
    }

    @Transactional
    public TestDto getTestForThePass(Long id) {
        Test entity = testJpaRepository.getOne(id);
        if(entity.getDeleted()) {
            return null;
        } else {
            return testUtils.convertEntityToDtoWithMarkdownPreprocessor(entity);
        }
    }

    private List<Test> findTestsByParagraph(Paragraph entity) {
        List<Test> result = new ArrayList<>();
        entity.getTests().forEach(result::add);
        entity.getChilds().forEach(s -> {
            s.getTests().forEach(e -> result.add(e));
        });
        return result;
    }

}

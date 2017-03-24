package com.resttest.utils;

import com.resttest.dto.testpassage.TestPassageDto;
import com.resttest.dto.answer.AnswerDto;
import com.resttest.model.TestPassage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Владислав on 23.03.2017.
 */
@Component
public class TestPassageUtils {

    @Autowired
    private UserUtils userUtils;

    @Autowired
    private QuestionUtils questionUtils;

    public TestPassageDto convertEntityToDto(TestPassage entity) {
        TestPassageDto dto = new TestPassageDto();
        dto.setId(entity.getId());
        dto.setName(entity.getTest().getName());
        dto.setQuestionDtos(questionUtils.convertEntitiesToDtosForMainModule(entity.getQuestions()));
        dto.setTestId(entity.getTest().getId());
        dto.setPassingUser(userUtils.convertEntityToDtoForMainModule(entity.getPassingUser()));
        return dto;
    }

}

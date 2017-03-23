package com.resttest.utils;

import com.resttest.dto.TestPassageDto;
import com.resttest.model.TestPassage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        dto.setName(entity.getName());
        dto.setPassingUser(userUtils.convertEntityToDto(entity.getPassingUser()));
        dto.setQuestionDtos(questionUtils.convertEntitiesToDtos(entity.getQuestions()));
        dto.setTestId(entity.getTest().getId());
        return dto;
    }

}

package com.resttest.utils;

import com.resttest.dto.ShortView;
import com.resttest.dto.answer.SimpleAnswerDto;
import com.resttest.dto.question.SimpleQuestionDto;
import com.resttest.dto.test.SimpleTestDto;
import com.resttest.dto.testprocessing.TestProcessingDto;
import com.resttest.model.Answer;
import com.resttest.model.Question;
import com.resttest.model.Test;
import com.resttest.model.TestProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Владислав on 28.03.2017.
 */
@Component
public class TestProcessingUtils {

    @Autowired
    private QuestionRepresentUtils questionRepresentUtils;

    public TestProcessingDto convertEntityToDto(TestProcessing entity) {
        TestProcessingDto dto = new TestProcessingDto();
        ShortView passingUser = new ShortView(entity.getPassingUser().getId(),
                entity.getPassingUser().getLastName() + " " +
                entity.getPassingUser().getFirstName() + " " + entity.getPassingUser().getMiddleName());
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPassingUser(passingUser);
        dto.setTest(getSimpleTest(entity.getTest()));
        dto.setQuestions(questionRepresentUtils.convertEntitiesToDtos(entity.getQuestionsRepresent()));
        dto.setTestAccess(new ShortView(entity.getTestAccess().getId(), entity.getTestAccess().getType()));
        return dto;
    }

    private SimpleTestDto getSimpleTest(Test entity) {
        SimpleTestDto dto = new SimpleTestDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setOwnerName(entity.getOwner().getLastName() + " " + entity.getOwner().getFirstName() +
                " " + entity.getOwner().getMiddleName());
        return dto;
    }


}

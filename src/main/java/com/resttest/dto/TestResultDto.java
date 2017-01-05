package com.resttest.dto;

import com.resttest.model.Test;
import com.resttest.model.TestResultQuestion;
import com.resttest.model.User;

import java.util.List;

/**
 * Created by kvasa on 01.01.2017.
 */
public class TestResultDto {

    private Long id;
    private Integer totalQuestions;
    private Integer rightAnswers;
    private List<TestResultQuestion> testResultQuestions;

}

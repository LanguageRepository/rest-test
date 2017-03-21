package com.resttest.repository;

import com.resttest.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by kvasa on 01.01.2017.
 */
public interface QuestionJpaRepository extends JpaRepository<Question, Long> {

    List<Question> findQuestionByTestId(Long testId);

}

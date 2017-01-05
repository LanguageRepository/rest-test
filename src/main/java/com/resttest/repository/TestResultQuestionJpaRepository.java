package com.resttest.repository;

import com.resttest.model.TestResultQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by kvasa on 01.01.2017.
 */
public interface TestResultQuestionJpaRepository extends JpaRepository<TestResultQuestion, Long> {

}

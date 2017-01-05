package com.resttest.repository;

import com.resttest.model.TestResultAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by kvasa on 01.01.2017.
 */
public interface TestResultAnswerJpaRepository extends JpaRepository<TestResultAnswer, Long> {
}

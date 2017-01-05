package com.resttest.repository;

import com.resttest.model.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by kvasa on 01.01.2017.
 */
public interface TestResultJpaRepository extends JpaRepository<TestResult, Long>{
}

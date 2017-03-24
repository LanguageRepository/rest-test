package com.resttest.repository;

import com.resttest.model.TestPassage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Владислав on 23.03.2017.
 */
public interface TestPassageJpaRepository extends JpaRepository<TestPassage, Long> {

    TestPassage findTestPassageByTest(Long id);

}

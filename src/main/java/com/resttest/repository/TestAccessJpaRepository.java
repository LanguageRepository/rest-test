package com.resttest.repository;

import com.resttest.model.TestAccess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestAccessJpaRepository extends JpaRepository<TestAccess, Long> {

    TestAccess findTestAccessByUsers(Long userId);

}

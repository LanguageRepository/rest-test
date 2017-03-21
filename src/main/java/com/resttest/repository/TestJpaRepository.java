package com.resttest.repository;

import com.resttest.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by kvasa on 01.01.2017.
 */
public interface TestJpaRepository extends JpaRepository<Test, Long>{

    List<Test> findTestsByParagraphId(Long id);

}

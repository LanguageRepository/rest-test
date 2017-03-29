package com.resttest.repository;

import com.resttest.model.QuestionRepresent;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Владислав on 28.03.2017.
 */
public interface QuestionRepresentJpaRepository extends JpaRepository<QuestionRepresent, Long> {
}

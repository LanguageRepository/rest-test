package com.resttest.repository;

import com.resttest.model.Paragraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by kvasa on 01.01.2017.
 */
public interface ParagraphJpaRepository extends JpaRepository<Paragraph, Long> {

}

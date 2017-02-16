package com.resttest.service;

import com.resttest.dto.test.TestDto;
import com.resttest.dto.test.TestDtoForTable;
import com.resttest.repository.TestJpaRepository;
import com.resttest.utils.TestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TestService {

    @Autowired
    private TestUtils testUtils;

    @Autowired
    private TestJpaRepository testJpaRepository;

    @Transactional
    public TestDto getTest(Long id) {
        return testUtils.convertEntityToDto(testJpaRepository.getOne(id));
    }

    @Transactional
    public TestDtoForTable createTest(TestDto dto) {
        return testUtils.convertEntityToShortDto(testJpaRepository.save(testUtils.convertDtoToEntity(dto)));
    }

    @Transactional
    public void updateTest(TestDto dto) {
        testJpaRepository.save(testUtils.convertDtoToEntity(dto));
    }

    @Transactional
    public void deleteTest(Long id) {
        testJpaRepository.delete(id);
    }

    @Transactional
    public List<TestDtoForTable> getAllTestsForTable() {
        return testUtils.convertEntitiesToDtosForTable(testJpaRepository.findAll());
    }

}

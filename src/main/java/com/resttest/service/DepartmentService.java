package com.resttest.service;

import com.resttest.dto.department.DepartmentDto;
import com.resttest.dto.department.ShortViewForDepartment;
import com.resttest.repository.DepartmentJpaRepository;
import com.resttest.utils.DepartmentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kvasa on 15.01.2017.
 */
@Service
@Transactional
public class DepartmentService {

    @Autowired
    private DepartmentJpaRepository departmentJpaRepository;

    @Autowired
    private DepartmentUtils departmentUtils;

    @Transactional
    public DepartmentDto getDepartment(Long id) {
        return departmentUtils.convertEntityToDto(departmentJpaRepository.getOne(id));
    }

    @Transactional
    public void createDepartment(DepartmentDto dto) {
        departmentUtils.convertEntityToDto(departmentJpaRepository.save(departmentUtils.convertDtoToEntity(dto)));
    }

    @Transactional
    public void deleteDepartment(Long id) {
        departmentJpaRepository.delete(id);
    }

    @Transactional
    public void updateDepartment(DepartmentDto dto) {
        departmentJpaRepository.saveAndFlush(departmentUtils.convertDtoToEntity(dto));
    }

    @Transactional
    public ShortViewForDepartment getAllDepartment() {
        return departmentUtils.convertDepartmentToShortView(departmentJpaRepository.getOne(1l));
    }

}

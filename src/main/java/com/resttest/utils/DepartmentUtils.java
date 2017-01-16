package com.resttest.utils;

import com.resttest.dto.ShortView;
import com.resttest.dto.department.DepartmentDto;
import com.resttest.dto.department.ShortViewForDepartment;
import com.resttest.model.Department;
import com.resttest.repository.DepartmentJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kvasa on 15.01.2017.
 */
@Component
public class DepartmentUtils {

    @Autowired
    private DepartmentJpaRepository departmentJpaRepository;

    @Autowired
    private DepartmentUtils departmentUtils;

    public DepartmentDto convertEntityToDto(Department department) {
        DepartmentDto dto = new DepartmentDto();
        dto.setId(department.getId());
        dto.setName(department.getName());
        dto.setDescription(department.getDescription());
        dto.setParent(departmentUtils.convertEntityToDto(departmentJpaRepository.getOne(department.getParent().getId())));
        dto.setChilds(new DepartmentUtils().convertDepartmentToShortViews(department.getChilds()));
        dto.setType(department.getType());
        return dto;
    }

    public Department convertDtoToEntity(DepartmentDto dto) {
        Department entity = new Department();
        entity.setName(dto.getName());
        entity.setType(dto.getType());
        entity.setParent(new DepartmentUtils().convertDtoToEntity(dto.getParent()));
        entity.setDescription(dto.getDescription());
        return entity;
    }

    public List<ShortViewForDepartment> convertDepartmentToShortViews(List<Department> entities) {
        List<ShortViewForDepartment> views = new ArrayList<>();
        for(int i = 0; i < entities.size(); i++) {
            ShortViewForDepartment shortView = new ShortViewForDepartment();
            shortView.setId(entities.get(i).getId());
            shortView.setName(entities.get(i).getName());
            shortView.setType(entities.get(i).getType());
            shortView.setChilds(new DepartmentUtils().convertDepartmentToShortViews(entities.get(i).getChilds()));
            views.add(shortView);
        }
        return views;
    }

    public List<DepartmentDto> convertEntitiesToDtos(List<Department> entities) {
        List<DepartmentDto> dtos = new ArrayList<>();
        List<DepartmentDto> childs = new ArrayList<>();
        for(int i = 0; i < entities.size(); i++) {
            DepartmentDto dto = new DepartmentDto();
            dto.setId(entities.get(i).getId());
            dto.setDescription(entities.get(i).getDescription());
            dto.setType(entities.get(i).getType());
            for(int j = 0; j < entities.get(i).getChilds().size(); j++) {
                DepartmentDto child = new DepartmentDto();
                //TODO
            }
        }
    }

    public ShortView convertDepartmentToShortView(Department department) {
        ShortView shortView = new ShortView();
        shortView.setId(department.getId());
        shortView.setName(department.getName());
        return shortView;
    }

}

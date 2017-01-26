package com.resttest.utils;

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
    public DepartmentJpaRepository repository;

    @Autowired
    public DepartmentUtils utils;

    public DepartmentDto convertEntityToDto(Department entity) {
        DepartmentDto dto = new DepartmentDto();
        List<DepartmentDto> childs = new ArrayList<>();
        for (int i = 0; i < entity.getChilds().size(); i++) {
            childs.add(utils.convertEntityToDto(entity.getChilds().get(i)));
        }
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setType(entity.getType());
        dto.setChildren(childs);
        return dto;
    }

    public Department convertDtoToEntity(DepartmentDto dto) {
        Department entity = new Department();
        List<Department> childs = new ArrayList<>();
        for (int i = 0; i < dto.getChildren().size(); i++) {
            childs.add(repository.getOne(dto.getChildren().get(i).getId()));
        }
        entity.setType(dto.getType());
        entity.setId(dto.getId());
        entity.setDescription(dto.getDescription());
        entity.setParent(repository.getOne(dto.getParent_id()));
        entity.setChilds(childs);
        return entity;
    }

    public ShortViewForDepartment convertDepartmentToShortView(Department department) {
        ShortViewForDepartment shortView = new ShortViewForDepartment();
        List<ShortViewForDepartment> childs = new ArrayList<>();
        for (int i = 0; i < department.getChilds().size(); i++) {
            childs.add(utils.convertDepartmentToShortView(department.getChilds().get(i)));
        }
        shortView.setId(department.getId());
        shortView.setChildren(childs);
        shortView.setText(department.getName());
        return shortView;
    }

}

package com.resttest.utils;

import com.resttest.dto.department.DepartmentDto;
import com.resttest.dto.department.ShortViewForDepartment;
import com.resttest.dto.department.ShortViewWithType;
import com.resttest.model.Department;
import com.resttest.repository.DepartmentJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public List<ShortViewWithType> convertEntitiesToShortView(List<Department> entities) {
        List<ShortViewWithType> shortViews = new ArrayList<>();
        for (Department entity : entities) {
            ShortViewWithType shortView = new ShortViewWithType();
            shortView.setId(entity.getId());
            shortView.setName(entity.getName());
            shortView.setType(entity.getType());
            shortViews.add(shortView);
        }
        return shortViews;
    }

    public Department convertShortViewToEntity(ShortViewWithType shortView) {
        Department department = new Department();
        if(Objects.equals(repository.getOne(shortView.getParent_id()).getType(), "unv")) {
            shortView.setType("fclt");
        } else if(Objects.equals(repository.getOne(shortView.getParent_id()).getType(), "fclt")) {
            shortView.setType("caf");
        } else if(Objects.equals(repository.getOne(shortView.getParent_id()).getType(), "caf")) {
            shortView.setType("group");
        } else {
            shortView.setType("other");
        }
        department.setParent(repository.getOne(shortView.getParent_id()));
        department.setType(shortView.getType());
        department.setName(shortView.getName());
        return department;
    }

    public Department convertShortViewToEntityForPut(ShortViewWithType shortView) {
        Department department = repository.getOne(shortView.getParent_id());
        department.setName(shortView.getName());
        return department;
    }

}

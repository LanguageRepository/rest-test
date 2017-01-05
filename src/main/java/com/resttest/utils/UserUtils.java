package com.resttest.utils;

import com.resttest.dto.ShortView;
import com.resttest.dto.UserDto;
import com.resttest.model.User;
import com.resttest.repository.DepartmentJpaRepository;
import com.resttest.repository.RoleJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kvasa on 02.01.2017.
 */
@Component
public class UserUtils {

    @Autowired
    private DepartmentJpaRepository departmentJpaRepository;

    public UserDto convertEntityToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setDescription(user.getDescription());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setMiddleName(user.getMiddleName());
        dto.setLastName(user.getLastName());
        dto.setPassword(user.getPassword());
        dto.setUsername(user.getUsername());
        dto.setPhone(user.getPhone());
        dto.setRoles(user.getRoles());
        dto.setDepartment(user.getDepartment().getName());
        return dto;
    }

    public User convertDtoToEntity(UserDto dto) {
        User entity = new User();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setRoles(dto.getRoles());
        entity.setDepartment(departmentJpaRepository.getDepartmentByName(dto.getDepartment()));
        entity.setPhone(dto.getPhone());
        entity.setDescription(dto.getDescription());
        entity.setFirstName(dto.getFirstName());
        entity.setMiddleName(dto.getMiddleName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        return entity;
    }

    public List<UserDto> convertEntitiesToDtos(List<User> entities) {
        List<UserDto> dtos = new ArrayList<>();
        for(int i = 0; i < entities.size(); i++) {
            UserDto dto = new UserDto();
            dto.setId(entities.get(i).getId());
            dto.setDescription(entities.get(i).getDescription());
            dto.setEmail(entities.get(i).getEmail());
            dto.setFirstName(entities.get(i).getFirstName());
            dto.setMiddleName(entities.get(i).getMiddleName());
            dto.setLastName(entities.get(i).getLastName());
            dto.setPassword(entities.get(i).getPassword());
            dto.setUsername(entities.get(i).getUsername());
            dto.setPhone(entities.get(i).getPhone());
            dto.setRoles(entities.get(i).getRoles());
            dto.setDepartment(entities.get(i).getDepartment().getName());
            dtos.add(dto);
        }
        return dtos;
    }

    public ShortView convertUserToShortView(User user) {
        ShortView shortView = new ShortView();
        shortView.setId(user.getId());
        shortView.setName(user.getUsername());
        return shortView;
    }

    public List<ShortView> convertUsersToShortViews(List<User> entities) {
        List<ShortView> shortViews = new ArrayList<>();
        for(int i = 0; i < entities.size(); i++) {
            ShortView shortView = new ShortView();
            shortView.setId(entities.get(i).getId());
            shortView.setName(entities.get(i).getUsername());
            shortViews.add(shortView);
        }
        return shortViews;
    }

}

package com.resttest.utils;

import com.resttest.dto.testaccess.TestAccessDto;
import com.resttest.dto.testaccess.TestAccessDtoForTable;
import com.resttest.model.TestAccess;
import com.resttest.model.User;
import com.resttest.repository.TestAccessJpaRepository;
import com.resttest.repository.TestJpaRepository;
import com.resttest.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class TestAccessUtils {

    @Autowired
    private TestUtils testUtils;

    @Autowired
    private TestJpaRepository testJpaRepository;

    @Autowired
    private UserUtils userUtils;

    @Autowired
    private UserJpaRepository userJpaRepository;

    public TestAccessDto convertEntityToDto(TestAccess entity) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        TestAccessDto dto = new TestAccessDto();
        dto.setId(entity.getId());
        dto.setTestDto(testUtils.convertEntityToDto(entity.getTest()));
        dto.setUsers(userUtils.convertUsersToShortViews(entity.getUsers()));
        dto.setInitialTerm(dateFormat.format(entity.getInitialTerm()));
        dto.setDeadline(dateFormat.format(entity.getDeadline()));
        dto.setType(entity.getType());
        dto.setNumberOfAttempts(entity.getNumberOfAttempts());
        dto.setTimeToPass(entity.getTimeToPass());
        return dto;
    }

    public TestAccess convertDtoToEntity(TestAccessDto dto) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        TestAccess entity = new TestAccess();
        List<User> userList = new ArrayList<>();
        entity.setId(dto.getId());
        entity.setTest(testJpaRepository.getOne(dto.getTestDto().getId()));
        entity.setDeadline(dateFormat.parse(dto.getDeadline()).getTime());
        entity.setInitialTerm(dateFormat.parse(dto.getInitialTerm()).getTime());
        for (int i = 0; i < dto.getUsers().size(); i++) {
            userList.add(userJpaRepository.getOne(dto.getUsers().get(i).getId()));
        }
        entity.setUsers(userList);
        entity.setNumberOfAttempts(dto.getNumberOfAttempts());
        entity.setType(dto.getType());
        entity.setTimeToPass(dto.getTimeToPass());

        return entity;
    }

    public List<TestAccessDtoForTable> convertEntitiesToDtosForTable(List<TestAccess> entities) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        List<TestAccessDtoForTable> dtos = new ArrayList<>();
        for (TestAccess entity : entities) {
            TestAccessDtoForTable dto = new TestAccessDtoForTable();
            dto.setId(entity.getId());
            dto.setTestOwner(entity.getTest().getOwner().getUsername());
            dto.setTestName(entity.getTest().getName());
            if(entity.getInitialTerm() != null && entity.getDeadline() != null) {
                dto.setInitialTerm(dateFormat.format(entity.getInitialTerm()));
                dto.setDeadline(dateFormat.format(entity.getDeadline()));
            }
            dto.setDepartments(selectDepartmentsForUsers(entity.getUsers()));
            dto.setType(entity.getType());
            dto.setActive(isActive(entity));
            dtos.add(dto);
        }
        return dtos;
    }

    private Set<String> selectDepartmentsForUsers(List<User> users) {
        Set<String> departments = new HashSet<>();
        for (User user : users) {
            departments.add(user.getDepartment().getName());
        }
        return departments;
    }

    private Boolean isActive(TestAccess entity) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        String valueOfCurrentDate = dateFormat.format(new Date().getTime());
        Date currentDate = new Date();
        Date initialTerm = new Date(entity.getInitialTerm());
        Date deadline = new Date(entity.getDeadline());
        if(initialTerm.after(currentDate) || deadline.before(currentDate)) {
            return false;
        } else {
            return true;
        }
    }

}

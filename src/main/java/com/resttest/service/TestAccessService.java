package com.resttest.service;

import com.resttest.dto.testaccess.TestAccessDto;
import com.resttest.dto.testaccess.TestAccessDtoForTable;
import com.resttest.dto.user.UserDtoForTable;
import com.resttest.model.TestAccess;
import com.resttest.repository.TestAccessJpaRepository;
import com.resttest.repository.UserJpaRepository;
import com.resttest.utils.RestResult;
import com.resttest.utils.TestAccessUtils;
import com.resttest.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class TestAccessService {

    @Autowired
    private TestAccessUtils utils;

    @Autowired
    private UserUtils userUtils;

    @Autowired
    private TestAccessJpaRepository jpaRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Transactional
    public TestAccessDto getTestAccessById(Long id) {
        return utils.convertEntityToDto(jpaRepository.getOne(id));
    }

    @Transactional
    public RestResult createTestAccess(TestAccessDto dto) throws ParseException {
        jpaRepository.save(utils.convertDtoToEntity(dto));
        return new RestResult("200", "TestAccess was updated");
    }

    @Transactional
    public List<TestAccessDtoForTable> getAllTestAccessesForTable() throws ParseException {
        return utils.convertEntitiesToDtosForTable(jpaRepository.findAll());
    }

    @Transactional
    public List<UserDtoForTable> findUserByTestAccess(Long id) {
        List<UserDtoForTable> allUsers = userUtils.convertEntitiesToDtoForTable(userJpaRepository.findAll());
        List<UserDtoForTable> usersWithAccess = userUtils.convertEntitiesToDtoForTable(jpaRepository.getOne(id).getUsers());
        for (UserDtoForTable allUser : allUsers) {
            for (UserDtoForTable usersWithAcces : usersWithAccess) {
                if (Objects.equals(allUser.getId(), usersWithAcces.getId())) {
                    allUser.setAccessByCurrentTest(true);
                }
            }
        }
        return allUsers.stream().sorted(((o1, o2) ->
                o2.getAccessByCurrentTest().compareTo(o1.getAccessByCurrentTest()))).
                collect(Collectors.toList());
    }

    public List<String> getDatesByTestAccess(Long id) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        List<String> dates = new ArrayList<>();
        dates.add(dateFormat.format(jpaRepository.getOne(id).getInitialTerm()));
        dates.add(dateFormat.format(jpaRepository.getOne(id).getDeadline()));
        return dates;
    }

    public List<TestAccessDtoForTable> getEntitiesForMainModule() throws ParseException {
        Long currentUserId = userService.getCurrentAuthenticatedUser().getId();
        List<TestAccess> allTestAccess = jpaRepository.findAll();
        List<TestAccess> testAccessesForCurrentUser = new ArrayList<>();
        for (TestAccess allTestAcces : allTestAccess) {
            for (int j = 0; j < allTestAcces.getUsers().size(); j++) {
                if (Objects.equals(allTestAcces.getUsers().get(j).getId(), currentUserId)) {
                    testAccessesForCurrentUser.add(allTestAcces);
                }
            }
        }
        return utils.convertEntitiesToDtosForTable(testAccessesForCurrentUser);
    }

}

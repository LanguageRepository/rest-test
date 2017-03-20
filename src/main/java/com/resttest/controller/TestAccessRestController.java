package com.resttest.controller;

import com.resttest.dto.testaccess.TestAccessDto;
import com.resttest.dto.testaccess.TestAccessDtoForTable;
import com.resttest.dto.user.UserDtoForTable;
import com.resttest.service.TestAccessService;
import com.resttest.service.UserService;
import com.resttest.utils.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(value = "/testaccess")
public class TestAccessRestController {

    @Autowired
    private TestAccessService service;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public TestAccessDto getTestAccessById(@PathVariable Long id) {
        return service.getTestAccessById(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public RestResult createTestAccess(@RequestBody TestAccessDto dto) throws ParseException {
        return service.createTestAccess(dto);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public List<TestAccessDtoForTable> getAllTestAccessesForTable() throws ParseException {
        return service.getAllTestAccessesForTable();
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = "application/json")
    public List<UserDtoForTable> findAndSortUserByTestAccess(@PathVariable Long id) {
        return service.findUserByTestAccess(id);
    }

    @RequestMapping(value = "/timing/{id}", method = RequestMethod.GET, produces = "application/json")
    public List<String> getDatesByTestAccess(@PathVariable Long id) {
        return service.getDatesByTestAccess(id);
    }

    @RequestMapping(value = "/currentuser", method = RequestMethod.GET, produces = "application/json")
    public List<TestAccessDtoForTable> getTestAccessesForCurrentUser() throws ParseException {
        return service.getEntitiesForMainModule();
    }

}

package com.resttest.controller;

import com.resttest.dto.department.DepartmentDto;
import com.resttest.dto.department.ShortViewForDepartment;
import com.resttest.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by kvasa on 15.01.2017.
 */
@RestController
@RequestMapping(value = "/department")
public class DepartmentRestController {

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public DepartmentDto getDepartment(@PathVariable("id") Long id) {
        return departmentService.getDepartment(id);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public void createDepartment(@RequestBody DepartmentDto dto) {
        departmentService.createDepartment(dto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteDepartment(@PathVariable("id") Long id) {
        departmentService.deleteDepartment(id);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    public void updateDepartment(@RequestBody DepartmentDto dto) {
        departmentService.updateDepartment(dto);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public ShortViewForDepartment getAllDepartment() {
        return departmentService.getAllDepartment();
    }

}

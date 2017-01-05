package com.resttest.controller;

import com.resttest.dto.ShortView;
import com.resttest.dto.UserDto;
import com.resttest.model.User;
import com.resttest.service.UserService;
import com.resttest.utils.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by kvasa on 02.01.2017.
 */
@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json")
    public UserDto getUser(@PathVariable("id") Long id) {
        return userService.getUser(id);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ShortView createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    public ShortView updateUser(@RequestBody UserDto userDto) {
        return userService.updateUser(userDto);
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE, produces = "application/json")
    public RestResult deleteUser(@PathVariable("id") Long id) {
        return userService.deleteUser(id);
    }

    @RequestMapping(value = "/get/short/{id}", method = RequestMethod.GET, produces = "application/json")
    public ShortView getShortUser(@PathVariable("id") Long id) {
        return userService.getShortUser(id);
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET, produces = "application/json")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/get/short", method = RequestMethod.GET, produces = "application/json")
    public List<ShortView> getShortUsers() {
        return userService.getShortUsers();
    }

}

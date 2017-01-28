package com.resttest.controller;

import com.resttest.dto.ShortView;
import com.resttest.dto.user.UserDto;
import com.resttest.dto.user.UserDtoForTable;
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

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public UserDto getUser(@PathVariable("id") Long id) {
        return userService.getUser(id);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    public ShortView updateUser(@RequestBody UserDto userDto) {
        return userService.updateUser(userDto);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE, produces = "application/json")
    public RestResult deleteUser(@PathVariable("id") Long id) {
        return userService.deleteUser(id);
    }

    @RequestMapping(value = "/short/{id}", method = RequestMethod.GET, produces = "application/json")
    public ShortView getShortUser(@PathVariable("id") Long id) {
        return userService.getShortUser(id);
    }

    /*All users is a dto for table on ap in this controller*/
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/short", method = RequestMethod.GET, produces = "application/json")
    public List<ShortView> getShortUsers() {
        return userService.getShortUsers();
    }

    @RequestMapping(value = "/changepassword", method = RequestMethod.PUT, produces = "application/json")
    public RestResult changePasswordForUser(@RequestBody UserDto dto) {
        userService.changePassword(dto);
        return new RestResult("" + dto.getPassword());
    }

    @RequestMapping(value = "/fortable", method = RequestMethod.GET, produces = "application/json")
    public List<UserDtoForTable> getUsersForTable() {
        return userService.getUsersForTable();
    }

    @RequestMapping(value = "/byname/{username}", method = RequestMethod.GET, produces = "application/json")
    public UserDto getUserByUsername(@PathVariable("username") String username) {
        return userService.getUserByUsername(username);
    }

    @RequestMapping(value = "/short/byname/{username}", method = RequestMethod.GET, produces = "application/json")
    public ShortView getShortUserByUsername(@PathVariable String username) {
        return userService.getShortUserByUsername(username);
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public UserDto getUsernameCurrentUser() {
        return userService.getCurrentAuthenticatedUser();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public RestResult saveDelete(@PathVariable("id") Long id) {
        userService.saveDelete(id);
        return new RestResult("OK", "Пользователь заморожен");
    }

}

package com.resttest.service;

import com.resttest.dto.ShortView;
import com.resttest.dto.UserDto;
import com.resttest.dto.UserDtoForTable;
import com.resttest.model.User;
import com.resttest.repository.UserJpaRepository;
import com.resttest.utils.RestResult;
import com.resttest.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kvasa on 02.01.2017.
 */
@Service
@Transactional
public class UserService {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private UserUtils userUtils;

    @Transactional
    public User getUser(Long id) {
        return userJpaRepository.getOne(id);
    }

    @Transactional
    public ShortView createUser(UserDto dto) {
        User user = userJpaRepository.save(userUtils.convertDtoToEntity(dto));
        return userUtils.convertUserToShortView(user);
    }

    @Transactional
    public ShortView updateUser(UserDto dto) {
        User user = userJpaRepository.saveAndFlush(userUtils.convertDtoToEntity(dto));
        return userUtils.convertUserToShortView(user);
    }

    @Transactional
    public RestResult deleteUser(Long id) {
        userJpaRepository.delete(id);
        RestResult restResult = new RestResult("OK");
        if(userJpaRepository.exists(id)) {
            restResult = new RestResult("ERROR", "User not delete");
            return restResult;
        }
        return restResult;
    }

    @Transactional
    public ShortView getShortUser(Long id) {
        return userUtils.convertUserToShortView(userJpaRepository.findOne(id));
    }

    @Transactional
    public List<UserDto> getAllUsers() {
        return userUtils.convertEntitiesToDtos(userJpaRepository.findAll());
    }

    @Transactional
    public List<ShortView> getShortUsers() {
        return userUtils.convertUsersToShortViews(userJpaRepository.findAll());
    }

    public User getUserByUsername(String username) {
        return userJpaRepository.getUserByUsername(username);
    }

    @Transactional
    public void changePassword(UserDto dto) {
        User user = userJpaRepository.getUserByUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        userJpaRepository.saveAndFlush(user);
    }

    @Transactional
    public List<UserDtoForTable> getUsersForTable() {
        return userUtils.convertEntitiesToDtoForTable(userJpaRepository.findAll());
    }

}

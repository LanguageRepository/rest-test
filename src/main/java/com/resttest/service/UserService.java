package com.resttest.service;

import com.resttest.dto.ShortView;
import com.resttest.dto.user.UserDto;
import com.resttest.dto.user.UserDtoForTable;
import com.resttest.model.User;
import com.resttest.repository.UserJpaRepository;
import com.resttest.utils.RestResult;
import com.resttest.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    public UserDto getUser(Long id) {
        return userUtils.convertEntityToDto(userJpaRepository.getOne(id));
    }

    @Transactional
    public UserDto createUser(UserDto dto) {
        return userUtils.convertEntityToDto(
                userJpaRepository.save(userUtils.convertDtoToEntityForPost(dto)));
    }

    @Transactional
    public ShortView updateUser(UserDto dto) {
        return userUtils.convertUserToShortView(
                userJpaRepository.save(userUtils.convertDtoToEntityForPut(dto)));
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

    @Transactional
    public UserDto getUserByUsername(String username) {
        return userUtils.convertEntityToDto(userJpaRepository.getUserByUsername(username));
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

    @Transactional
    public ShortView getShortUserByUsername(String username) {
        return userUtils.convertUserToShortView(userJpaRepository.getUserByUsername(username));
    }

    @Transactional
    public User getSecureUserByUsername(String username) {
        return userJpaRepository.getUserByUsername(username);
    }

    @Transactional
    public UserDto getCurrentAuthenticatedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if(principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return userUtils.convertEntityToDto(userJpaRepository.getUserByUsername(username));
    }

    public void saveDelete(Long id) {
        userJpaRepository.getOne(id).setDeleted(true);
        userJpaRepository.getOne(id).setEnabled(false);
    }

}

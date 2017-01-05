package com.resttest.repository;

import com.resttest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by kvasa on 01.01.2017.
 */
public interface UserJpaRepository extends JpaRepository<User, Long> {

    User getUserByUsername(String username);

}

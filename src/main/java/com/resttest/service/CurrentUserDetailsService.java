package com.resttest.service;

import com.resttest.model.RoleEnum;
import com.resttest.model.User;
import com.resttest.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CurrentUserDetailsService implements UserDetailsService {

    @Autowired
    private UserJpaRepository jpaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = jpaRepository.getUserByUsername(username);
        List<GrantedAuthority> authorities = buildUserAuthority(user.getRole());
        return buildUserForAuthentication(user, authorities);
    }

    private org.springframework.security.core.userdetails.User
    buildUserForAuthentication(User user, List<GrantedAuthority> authority) {
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), user.isEnabled(),
                true, true,
                true, authority
        );
    }

    private List<GrantedAuthority> buildUserAuthority(RoleEnum role) {
        Set<GrantedAuthority> setAuth = new HashSet<>();
        setAuth.add(new SimpleGrantedAuthority(role.toString()));
        return new ArrayList<>(setAuth);
    }

}

package com.ssafy.findyourhome.service;

import com.ssafy.findyourhome.dao.UserDao;
import com.ssafy.findyourhome.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username " + username + " does not exist.");
        }

        Set<GrantedAuthority> authorities = new HashSet<>();
        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), authorities);
    }
}

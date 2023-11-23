package com.ssafy.findyourhome.security;

import com.ssafy.findyourhome.dao.UserDao;
import com.ssafy.findyourhome.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Slf4j
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private HttpServletRequest request;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User account = userDao.findByUsername(username);
        if (account == null) {
            if (userDao.findByUsername(username) == null) {
                throw new UsernameNotFoundException("No user found with username: " + username);
            }
        }
        ArrayList<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
        roles.add(new SimpleGrantedAuthority(account.getRole()));

        return new AccountContext(account, roles);
    }
}
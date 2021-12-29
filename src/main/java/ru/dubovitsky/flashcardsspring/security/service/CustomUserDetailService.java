package ru.dubovitsky.flashcardsspring.security.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final AppUserServiceDao userDetailsServiceDao;

    public CustomUserDetailService(@Qualifier("mock") AppUserServiceDao userDetailsServiceDao) {
        this.userDetailsServiceDao = userDetailsServiceDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = userDetailsServiceDao.getAppUserByName(username);
        return userDetails;
    }
}

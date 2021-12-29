package ru.dubovitsky.flashcardsspring.security.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.dubovitsky.flashcardsspring.model.User;
import ru.dubovitsky.flashcardsspring.security.model.AppUser;
import ru.dubovitsky.flashcardsspring.service.UserService;

import java.util.stream.Collectors;

@Service("database")
@AllArgsConstructor
public class UserDetailsServiceDatabaseImpl implements AppUserServiceDao {

    private final UserService userService;

    @Override
    public AppUser getAppUserByName(String username) throws UsernameNotFoundException {
        User databaseUser = userService.getUserByName(username);

        AppUser appUser = AppUser.builder()
                .username(databaseUser.getUsername())
                .password(databaseUser.getPassword())
                .authorities(databaseUser.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toSet()))
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .build();

        return appUser;
    }
}

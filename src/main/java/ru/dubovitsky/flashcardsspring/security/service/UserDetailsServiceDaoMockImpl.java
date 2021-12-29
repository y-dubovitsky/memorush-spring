package ru.dubovitsky.flashcardsspring.security.service;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.dubovitsky.flashcardsspring.security.model.AppUser;

@Service("mock")
@AllArgsConstructor
public class UserDetailsServiceDaoMockImpl implements AppUserServiceDao {

    private final PasswordEncoder passwordEncoder;

    @Override
    public AppUser getAppUserByName(String username) throws UsernameNotFoundException {
        AppUser appUser = AppUser.builder()
                .username("u")
                .password(passwordEncoder.encode("u"))
                .authorities(Lists.newArrayList(
                        new SimpleGrantedAuthority("ROLE_ADMIN"),
                        new SimpleGrantedAuthority("ROLE_USER")
                        ))
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .build();

        return appUser;
    }
}

package ru.dubovitsky.flashcardsspring.security.service;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.dubovitsky.flashcardsspring.model.enums.RoleEnum;
import ru.dubovitsky.flashcardsspring.security.model.AppUser;

import java.util.Set;

@Service("mock")
@AllArgsConstructor
public class UserDetailsServiceDaoMockImpl implements AppUserServiceDao {

    private final PasswordEncoder passwordEncoder;

    @Override
    public AppUser getAppUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = AppUser.builder()
                .username("u")
                .password(passwordEncoder.encode("u"))
                .authorities(Set.of(
                        new SimpleGrantedAuthority(RoleEnum.GUEST.name()),
                        new SimpleGrantedAuthority(RoleEnum.USER.name()),
                        new SimpleGrantedAuthority(RoleEnum.ADMIN.name()),
                        new SimpleGrantedAuthority(RoleEnum.SUPER_ADMIN.name())
                ))
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .build();

        return appUser;
    }
}

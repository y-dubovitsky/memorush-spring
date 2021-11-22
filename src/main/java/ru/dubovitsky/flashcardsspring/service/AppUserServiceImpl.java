package ru.dubovitsky.flashcardsspring.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.dubovitsky.flashcardsspring.model.AppUser;
import ru.dubovitsky.flashcardsspring.model.Role;
import ru.dubovitsky.flashcardsspring.repository.AppUserRepository;
import ru.dubovitsky.flashcardsspring.repository.RoleRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@Transactional //TODO ??
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final RoleRepository roleRepository;

    @Override
    public AppUser saveAppUser(AppUser user) {
        log.info("Saving new user to DB: {}", user.getName());
        return appUserRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role to DB: {}", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToAppUser(String username, String roleName) { //TODO add validation?
        AppUser appUser = appUserRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);

        appUser.getRoles().add(role);
    }

    @Override
    public AppUser getAppUser(String username) {
        return appUserRepository.findByUsername(username);
    }

    @Override
    public List<AppUser> getAppUsers() {
        return appUserRepository.findAll();
    }
}

package ru.dubovitsky.flashcardsspring.service;

import ru.dubovitsky.flashcardsspring.model.AppUser;
import ru.dubovitsky.flashcardsspring.model.Role;

import java.util.List;

public interface AppUserService {

    AppUser saveAppUser(AppUser user);
    Role saveRole(Role role);

    void addRoleToAppUser(String username, String roleName);
    AppUser getAppUser(String username);
    List<AppUser> getAppUsers();
}

package ru.dubovitsky.flashcardsspring.security.service;

import ru.dubovitsky.flashcardsspring.security.model.AppUser;

public interface AppUserServiceDao  {

    AppUser getAppUserByUsername(String name);

}

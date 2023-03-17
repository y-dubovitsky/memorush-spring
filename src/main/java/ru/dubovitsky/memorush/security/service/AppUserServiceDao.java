package ru.dubovitsky.memorush.security.service;

import ru.dubovitsky.memorush.security.model.AppUser;

public interface AppUserServiceDao  {

    AppUser getAppUserByUsername(String name);

}

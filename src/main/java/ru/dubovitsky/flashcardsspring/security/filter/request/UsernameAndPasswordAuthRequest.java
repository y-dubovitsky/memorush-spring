package ru.dubovitsky.flashcardsspring.security.filter.request;

import lombok.Data;

@Data
public class UsernameAndPasswordAuthRequest {

    private String username;
    private String password;

}

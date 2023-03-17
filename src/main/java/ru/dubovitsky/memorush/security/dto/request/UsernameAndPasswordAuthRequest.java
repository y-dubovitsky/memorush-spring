package ru.dubovitsky.memorush.security.dto.request;

import lombok.Data;

@Data
public class UsernameAndPasswordAuthRequest {

    private String username;
    private String password;

}

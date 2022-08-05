package ru.dubovitsky.flashcardsspring.security.dto.request;

import lombok.Data;

@Data
public class UsernameAndPasswordAuthRequest {

    private String username;
    private String password;

}

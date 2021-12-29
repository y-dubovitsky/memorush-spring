package ru.dubovitsky.flashcardsspring.security.filter.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenVerifierFilterResponse {

    private String token;
    private String username;

}

package ru.dubovitsky.flashcardsspring.dto.request;

import lombok.*;

@Builder
@Data
@Getter @Setter
@AllArgsConstructor
public class UserLoginRequestDto {

    private String username;
    private String password;

}

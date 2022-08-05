package ru.dubovitsky.flashcardsspring.dto.response;

import lombok.*;

@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
public class UserResponseDto {

    private String firstName;
    private String lastName;
    private String username;
    private String email;

}

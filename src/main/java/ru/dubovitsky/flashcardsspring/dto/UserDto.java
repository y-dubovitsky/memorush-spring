package ru.dubovitsky.flashcardsspring.dto;

import lombok.*;

@Builder
@Data
@Getter @Setter
@AllArgsConstructor
public class UserDto {

    private String username;

}

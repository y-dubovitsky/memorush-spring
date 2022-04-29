package ru.dubovitsky.flashcardsspring.facade;

import ru.dubovitsky.flashcardsspring.dto.UserDto;
import ru.dubovitsky.flashcardsspring.model.User;

public class UserFacade {

    public static UserDto userToUserDto(User user) {
        return UserDto.builder()
                .username(user.getUsername())
                .build();
    }

}

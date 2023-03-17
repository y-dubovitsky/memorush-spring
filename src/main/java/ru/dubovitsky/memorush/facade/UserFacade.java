package ru.dubovitsky.memorush.facade;

import ru.dubovitsky.memorush.dto.request.UserRegistrationRequestDto;
import ru.dubovitsky.memorush.dto.response.UserResponseDto;
import ru.dubovitsky.memorush.model.User;

public class UserFacade {

    public static User userRegistrationDtoToUser(UserRegistrationRequestDto userRegistrationRequestDto) {
        return User.builder()
                .username(userRegistrationRequestDto.getUsername())
                .password(userRegistrationRequestDto.getPassword())
                .password2(userRegistrationRequestDto.getPassword2())
                .email(userRegistrationRequestDto.getEmail())
                .build();
    }

    public static UserResponseDto userToUserResponseDto(User user) {
        return UserResponseDto.builder()
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }


}

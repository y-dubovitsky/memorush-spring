package ru.dubovitsky.flashcardsspring.api;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dubovitsky.flashcardsspring.dto.request.UserRegistrationRequestDto;
import ru.dubovitsky.flashcardsspring.dto.request.UserUpdateRequestDto;
import ru.dubovitsky.flashcardsspring.facade.UserFacade;
import ru.dubovitsky.flashcardsspring.model.User;
import ru.dubovitsky.flashcardsspring.service.UserService;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<?> registerNewUser(@RequestBody UserRegistrationRequestDto userRegistrationRequestDto) {
        User user = userService.addNewUser(UserFacade.userRegistrationDtoToUser(userRegistrationRequestDto));
        if (Objects.isNull(user)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(UserFacade.userToUserResponseDto(user));
    }

    @PutMapping("/updateUserData")
    public ResponseEntity<?> updateUserData(@RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        User user = userService.updateUser(userUpdateRequestDto);
        if (Objects.isNull(user)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(UserFacade.userToUserResponseDto(user));
    }
}

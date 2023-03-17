package ru.dubovitsky.memorush.api;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.dubovitsky.memorush.dto.request.UserRegistrationRequestDto;
import ru.dubovitsky.memorush.dto.request.UserUpdateRequestDto;
import ru.dubovitsky.memorush.facade.UserFacade;
import ru.dubovitsky.memorush.model.User;
import ru.dubovitsky.memorush.service.UserService;

import java.security.Principal;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<?> registerNewUser(@RequestBody UserRegistrationRequestDto userRegistrationRequestDto) {
        User user = userService.createNewUser(UserFacade.userRegistrationDtoToUser(userRegistrationRequestDto));
        if (Objects.isNull(user)) {
            return new ResponseEntity<>(
                    String.format("User %s already exists", userRegistrationRequestDto.getUsername()),
                            HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/updateUserData")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'SUPER_ADMIN', 'OWNER')")
    public ResponseEntity<?> updateUserData(
            @RequestBody UserUpdateRequestDto userUpdateRequestDto,
            Principal principal
    ) {
        User user = userService.updateUser(userUpdateRequestDto, principal);
        if (Objects.isNull(user)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(UserFacade.userToUserResponseDto(user));
    }
}

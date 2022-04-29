package ru.dubovitsky.flashcardsspring.api;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dubovitsky.flashcardsspring.facade.UserFacade;
import ru.dubovitsky.flashcardsspring.model.Role;
import ru.dubovitsky.flashcardsspring.model.User;
import ru.dubovitsky.flashcardsspring.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    //TODO Возвращать не пользователя, а Фасад
    @PostMapping("/registration")
    public ResponseEntity register(@RequestBody User user) {

        User userByName = userService.getUserByName(user.getUsername());

        if(userByName != null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        User registeredUser = userService.addUser(user);
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}

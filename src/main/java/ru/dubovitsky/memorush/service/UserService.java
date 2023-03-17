package ru.dubovitsky.memorush.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dubovitsky.memorush.dto.request.UserUpdateRequestDto;
import ru.dubovitsky.memorush.model.User;
import ru.dubovitsky.memorush.model.enums.RoleEnum;
import ru.dubovitsky.memorush.repository.UserRepository;

import java.security.Principal;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User getUserByUsername(String name) {
        return userRepository.findByUsername(name)
                .orElseThrow(() -> new RuntimeException(String.format("User with name - %s not found", name)));
    }

    public User createNewUser(User user) {
        boolean present = userRepository.findByUsername(user.getUsername()).isPresent();
        if (present) {
            throw new RuntimeException(String.format("User with name - %s already exists", user.getUsername()));
        }
        //TODO В каком месте добавить проверку?
        if (!user.getPassword().equals(user.getPassword2())) {
            throw new RuntimeException("Password doesn't equals password2");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPassword2(passwordEncoder.encode(user.getPassword2()));
        user.setRole(RoleEnum.USER);
        userRepository.save(user);
        log.info(String.format("New user %s registered", user.getUsername()));
        return user;
    }

    public User updateUser(UserUpdateRequestDto userUpdateRequestDto, Principal principal) {
        User user = getUserByPrincipal(principal);

        user.setPassword(passwordEncoder.encode(userUpdateRequestDto.getPassword()));
        user.setPassword2(passwordEncoder.encode(userUpdateRequestDto.getPassword2()));
        user.setEmail(userUpdateRequestDto.getEmail());
        User updatedUser = userRepository.save(user);
        log.info(String.format("User %s updated", user.getUsername()));
        return updatedUser;
    }

    //! Этот метод возвращает текущего пользователя, которого он берет из контекста Spring Security
    public User getCurrentUserByPrincipal(Principal principal) {
        return this.getUserByPrincipal(principal);
    }

    private User getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException(String.format("User with name %s not found", username)));
        return user;
    }

}

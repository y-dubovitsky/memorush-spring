package ru.dubovitsky.flashcardsspring.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dubovitsky.flashcardsspring.model.User;
import ru.dubovitsky.flashcardsspring.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserByName(String name) {
        return userRepository.findByUsername(name);
    }

    public User addUser(User user) {
        User savedUser = userRepository.save(user);
        return savedUser;
    }
}

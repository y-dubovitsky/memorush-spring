package ru.dubovitsky.flashcardsspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dubovitsky.flashcardsspring.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

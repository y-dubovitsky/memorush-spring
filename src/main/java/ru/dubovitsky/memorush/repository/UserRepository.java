package ru.dubovitsky.memorush.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dubovitsky.memorush.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}

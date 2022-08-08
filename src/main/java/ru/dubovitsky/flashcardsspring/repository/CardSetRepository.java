package ru.dubovitsky.flashcardsspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dubovitsky.flashcardsspring.model.CardSet;
import ru.dubovitsky.flashcardsspring.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CardSetRepository extends JpaRepository<CardSet, Long> {
    Optional<Set<CardSet>> findAllByUser(User user);
}

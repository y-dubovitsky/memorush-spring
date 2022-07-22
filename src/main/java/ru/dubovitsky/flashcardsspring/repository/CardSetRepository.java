package ru.dubovitsky.flashcardsspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dubovitsky.flashcardsspring.model.CardSet;

public interface CardSetRepository extends JpaRepository<CardSet, Integer> {
}

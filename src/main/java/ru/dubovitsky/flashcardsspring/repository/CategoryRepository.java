package ru.dubovitsky.flashcardsspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dubovitsky.flashcardsspring.model.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findByName(String name);

}

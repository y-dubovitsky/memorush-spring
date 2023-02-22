package ru.dubovitsky.flashcardsspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dubovitsky.flashcardsspring.model.Tag;

import java.util.Optional;
import java.util.Set;

public interface TagRepository extends JpaRepository<Tag, Integer> {

    Optional<Tag> findByName(String name);

}

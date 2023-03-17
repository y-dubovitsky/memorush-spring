package ru.dubovitsky.memorush.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dubovitsky.memorush.model.Tag;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Integer> {

    Optional<Tag> findByName(String name);

}

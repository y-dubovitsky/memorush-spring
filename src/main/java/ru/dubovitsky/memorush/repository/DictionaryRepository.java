package ru.dubovitsky.memorush.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.dubovitsky.memorush.model.Dictionary;

import java.util.List;
import java.util.Optional;

public interface DictionaryRepository extends JpaRepository<Dictionary, Short> {

    Optional<Dictionary> findByName(String name);

    @Query("SELECT name FROM Dictionary")
    Optional<List<String>> findAllNames();

}



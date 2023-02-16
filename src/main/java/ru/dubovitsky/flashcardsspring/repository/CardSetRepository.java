package ru.dubovitsky.flashcardsspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.dubovitsky.flashcardsspring.model.CardSet;
import ru.dubovitsky.flashcardsspring.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CardSetRepository extends JpaRepository<CardSet, Long> {
    Optional<Set<CardSet>> findAllByUser(User user);

    @Query(value = "SELECT c from CardSet c where lower(c.name) LIKE lower(CONCAT('%',:cardSetName,'%'))" +
            " or " +
            "lower(c.category.name) LIKE lower(CONCAT('%',:categoryName,'%'))")
    Optional<List<CardSet>> findByCardSetNameOrCategoryName(
            @Param("cardSetName") String cardSetName,
            @Param("categoryName") String categoryName);
}

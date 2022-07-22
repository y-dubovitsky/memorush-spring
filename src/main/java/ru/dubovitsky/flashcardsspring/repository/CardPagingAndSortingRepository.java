package ru.dubovitsky.flashcardsspring.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.dubovitsky.flashcardsspring.model.Card;

public interface CardPagingAndSortingRepository extends PagingAndSortingRepository<Card, Long> {
}

package ru.dubovitsky.flashcardsspring.service;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dubovitsky.flashcardsspring.model.Card;
import ru.dubovitsky.flashcardsspring.model.CardSet;
import ru.dubovitsky.flashcardsspring.repository.CardSetRepository;
import ru.dubovitsky.flashcardsspring.repository.CardPagingAndSortingRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CardService {

    private final CardPagingAndSortingRepository cardRepository;
    private final CardSetRepository cardSetRepository;

    public List<Card> getAllCard() {
        ArrayList<Card> cards = Lists.newArrayList(cardRepository.findAll());
        return cards;
    }

    public Card addCard(Card card) {
        Card savedCard = cardRepository.save(card);
        return savedCard;
    }

    public List<CardSet> getAllCardSet() {
        return cardSetRepository.findAll();
    }
}

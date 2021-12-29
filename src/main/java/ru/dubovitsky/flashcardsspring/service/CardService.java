package ru.dubovitsky.flashcardsspring.service;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dubovitsky.flashcardsspring.model.Card;
import ru.dubovitsky.flashcardsspring.repository.PagingAndSortingCardRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CardService {

    private final PagingAndSortingCardRepository cardRepository;

    public List<Card> getAllCard() {
        ArrayList<Card> cards = Lists.newArrayList(cardRepository.findAll());
        return cards;
    }

    public Card addCard(Card card) {
        Card savedCard = cardRepository.save(card);
        return savedCard;
    }

}

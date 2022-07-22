package ru.dubovitsky.flashcardsspring.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dubovitsky.flashcardsspring.dto.CardSetDto;
import ru.dubovitsky.flashcardsspring.facade.CardSetFacade;
import ru.dubovitsky.flashcardsspring.model.CardSet;
import ru.dubovitsky.flashcardsspring.repository.CardSetRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CardSetService {

    private CardSetRepository cardSetRepository;

    public void saveCardSet(CardSetDto cardSetDto) {
        CardSet savedCardSet = cardSetRepository.save(CardSetFacade.cardSetDtoToCardSet(cardSetDto));
    }

    public List<CardSet> getAllCardSets() {
        return cardSetRepository.findAll();
    }
}

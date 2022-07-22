package ru.dubovitsky.flashcardsspring.facade;

import ru.dubovitsky.flashcardsspring.dto.CardSetDto;
import ru.dubovitsky.flashcardsspring.model.CardSet;

import java.util.stream.Collectors;

public class CardSetFacade {

    public static CardSet cardSetDtoToCardSet(CardSetDto cardSetDto) {
        return CardSet.builder()
                .name(cardSetDto.getTitle())
                .description(cardSetDto.getDescription())
                .tags(cardSetDto.getTags())
                .cardList(cardSetDto.getFlashCardsData()
                        .stream()
                        .map(cardDto -> CardFacade.cardDtoToCard(cardDto))
                        .collect(Collectors.toList()))
                .build();
    }

}

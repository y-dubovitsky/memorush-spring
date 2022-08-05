package ru.dubovitsky.flashcardsspring.facade;

import ru.dubovitsky.flashcardsspring.dto.request.CardSetRequestDto;
import ru.dubovitsky.flashcardsspring.model.CardSet;

import java.util.stream.Collectors;

public class CardSetFacade {

    public static CardSet cardSetRequestDtoToCardSet(CardSetRequestDto cardSetRequestDto) {
        return CardSet.builder()
                .name(cardSetRequestDto.getTitle())
                .description(cardSetRequestDto.getDescription())
                .tags(cardSetRequestDto.getTags())
                .cardList(cardSetRequestDto.getFlashCardArray()
                        .stream()
                        .map(cardDto -> CardFacade.cardRequestDtoToCard(cardDto))
                        .collect(Collectors.toList()))
                .build();
    }

}

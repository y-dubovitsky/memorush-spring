package ru.dubovitsky.flashcardsspring.facade;

import ru.dubovitsky.flashcardsspring.dto.request.CardSetRequestDto;
import ru.dubovitsky.flashcardsspring.dto.response.CardSetResponseDto;
import ru.dubovitsky.flashcardsspring.model.CardSet;

import java.util.stream.Collectors;

public class CardSetFacade {

    public static CardSet cardSetRequestDtoToCardSet(CardSetRequestDto cardSetRequestDto) {
        return CardSet.builder()
                .name(cardSetRequestDto.getName())
                .description(cardSetRequestDto.getDescription())
                .tags(cardSetRequestDto.getTags())
                .cardList(cardSetRequestDto.getFlashCardArray()
                        .stream()
                        .map(cardDto -> CardFacade.cardRequestDtoToCard(cardDto))
                        .collect(Collectors.toSet()))
                .build();
    }

    public static CardSetResponseDto cardSetToCardSetResponseDto(CardSet cardSet) {
        return CardSetResponseDto.builder()
                .id(cardSet.getId())
                .name(cardSet.getName())
                .tags(cardSet.getTags())
                .description(cardSet.getDescription())
                .folder(cardSet.getFolder())
                .isFavorite(cardSet.isFavorite())
                .createdAt(cardSet.getCreatedAt())
                .updatedAt(cardSet.getUpdatedAt())
                .flashCardArray(
                        cardSet.getCardList()
                                .stream()
                                .map(card -> CardFacade.cardToCardResponseDto(card))
                                .collect(Collectors.toSet())
                ).build();
    }

}

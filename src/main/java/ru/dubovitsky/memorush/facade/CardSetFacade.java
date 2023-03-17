package ru.dubovitsky.memorush.facade;

import ru.dubovitsky.memorush.dto.request.CardSetRequestDto;
import ru.dubovitsky.memorush.dto.response.CardSetResponseDto;
import ru.dubovitsky.memorush.model.CardSet;

import java.util.stream.Collectors;

public class CardSetFacade {

    public static CardSet cardSetRequestDtoToCardSet(CardSetRequestDto cardSetRequestDto) {
        return CardSet.builder()
                .name(cardSetRequestDto.getName())
                .description(cardSetRequestDto.getDescription())
                .category(CategoryFacade.categoryNameToCategory(cardSetRequestDto.getCategoryName()))
                .tags(TagFacade.tagRequestDtoToTagSet(cardSetRequestDto.getTags()))
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
                .description(cardSet.getDescription())
                .categoryName(cardSet.getCategory().getName())
                .tags(cardSet.getTags()
                        .stream()
                        .map(tag -> tag.getName())
                        .collect(Collectors.toList()))
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

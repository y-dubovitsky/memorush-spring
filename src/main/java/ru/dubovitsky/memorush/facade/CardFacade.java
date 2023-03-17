package ru.dubovitsky.memorush.facade;

import ru.dubovitsky.memorush.dto.request.CardRequestDto;
import ru.dubovitsky.memorush.dto.response.CardResponseDto;
import ru.dubovitsky.memorush.model.Card;

public class CardFacade {

    public static Card cardRequestDtoToCard(CardRequestDto cardRequestDto) {
        return Card.builder()
                .frontSide(cardRequestDto.getFrontSide())
                .backSide(cardRequestDto.getBackSide())
                .hint(cardRequestDto.getHint())
                .build();
    }

    public static CardResponseDto cardToCardResponseDto(Card card) {
        return CardResponseDto.builder()
                .id(card.getId())
                .frontSide(card.getFrontSide())
                .backSide(card.getBackSide())
                .hint(card.getHint())
                .isFavorite(card.isFavorite())
                .isLearned(card.isLearned())
                .build();
    }

}

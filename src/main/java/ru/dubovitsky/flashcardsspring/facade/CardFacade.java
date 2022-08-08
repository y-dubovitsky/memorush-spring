package ru.dubovitsky.flashcardsspring.facade;

import ru.dubovitsky.flashcardsspring.dto.request.CardRequestDto;
import ru.dubovitsky.flashcardsspring.dto.response.CardResponseDto;
import ru.dubovitsky.flashcardsspring.model.Card;

import java.util.Optional;

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

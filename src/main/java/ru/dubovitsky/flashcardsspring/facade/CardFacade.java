package ru.dubovitsky.flashcardsspring.facade;

import ru.dubovitsky.flashcardsspring.dto.request.CardRequestDto;
import ru.dubovitsky.flashcardsspring.model.Card;

public class CardFacade {

    public static Card cardRequestDtoToCard(CardRequestDto cardRequestDto) {
        return Card.builder()
                .frontSide(cardRequestDto.getFrontSide())
                .backSide(cardRequestDto.getBackSide())
                .hint(cardRequestDto.getHint())
                .build();
    }

}

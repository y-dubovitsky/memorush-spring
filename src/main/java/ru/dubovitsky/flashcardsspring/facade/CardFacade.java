package ru.dubovitsky.flashcardsspring.facade;

import ru.dubovitsky.flashcardsspring.dto.CardDto;
import ru.dubovitsky.flashcardsspring.model.Card;

public class CardFacade {

    public static Card cardDtoToCard(CardDto cardDto) {
        return Card.builder()
                .frontSide(cardDto.getFrontSide())
                .backSide(cardDto.getBackSide())
                .hint(cardDto.getHint())
                .build();
    }

}

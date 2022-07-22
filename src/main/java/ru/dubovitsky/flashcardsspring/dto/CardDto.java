package ru.dubovitsky.flashcardsspring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
@AllArgsConstructor
public class CardDto {

    private String frontSide;
    private String backSide;
    private String hint;

}

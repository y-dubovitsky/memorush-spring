package ru.dubovitsky.flashcardsspring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter @Setter
@Builder
public class CardSetDto {

    private String title;
    private String tags;
    private String description;

    private List<CardDto> flashCardsData;

}

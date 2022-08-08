package ru.dubovitsky.flashcardsspring.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@Getter @Setter
@Builder
public class CardSetRequestDto {

    private String title;
    private String tags;
    private String description;

    private Set<CardRequestDto> flashCardArray;

}

package ru.dubovitsky.memorush.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@Getter @Setter
@Builder
public class CardSetRequestDto {

    private String name;
    private Set<String> tags;
    private String description;
    private String categoryName;
    private boolean isFavorite;

    private Set<CardRequestDto> flashCardArray;

}

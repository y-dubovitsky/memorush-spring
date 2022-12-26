package ru.dubovitsky.flashcardsspring.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class CategoryRequestDto {

    private String categoryName;

}

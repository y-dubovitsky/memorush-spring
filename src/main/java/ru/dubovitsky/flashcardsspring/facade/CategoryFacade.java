package ru.dubovitsky.flashcardsspring.facade;

import ru.dubovitsky.flashcardsspring.dto.request.CategoryRequestDto;
import ru.dubovitsky.flashcardsspring.model.Category;

public class CategoryFacade {

    public static Category categoryRequestDtoToCategory(CategoryRequestDto categoryRequestDto) {
        return Category.builder()
                .name(categoryRequestDto.getCategoryName())
                .build();
    }

    public static Category categoryNameToCategory(String name) {
        return Category.builder()
                .name(name)
                .build();
    }

}

package ru.dubovitsky.memorush.facade;

import ru.dubovitsky.memorush.dto.request.CategoryRequestDto;
import ru.dubovitsky.memorush.model.Category;

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

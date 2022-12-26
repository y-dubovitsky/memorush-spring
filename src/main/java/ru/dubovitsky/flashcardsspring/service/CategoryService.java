package ru.dubovitsky.flashcardsspring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.dubovitsky.flashcardsspring.model.Category;
import ru.dubovitsky.flashcardsspring.repository.CategoryRepository;

import java.util.Optional;

@Slf4j
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category category) {
        Optional<Category> savedCategory = categoryRepository.findByName(category.getName());
        if (savedCategory.isPresent()) {
            log.info(String.format("Category with name %s already exists", category.getName()));
            return savedCategory.get();
        } else {
            return categoryRepository.save(category);
        }
    }
}

package ru.dubovitsky.flashcardsspring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dubovitsky.flashcardsspring.model.Tag;
import ru.dubovitsky.flashcardsspring.repository.TagRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Set<Tag> createTagCollection(Set<Tag> tags) {
        return tags.stream().map(tag -> saveTagIfNotExists(tag)).collect(Collectors.toSet());
    }

    public Tag saveTagIfNotExists(Tag tag) {
        return tagRepository.findByName(tag.getName()).orElseGet(() -> tagRepository.save(tag));
    }
}

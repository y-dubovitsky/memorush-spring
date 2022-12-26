package ru.dubovitsky.flashcardsspring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.dubovitsky.flashcardsspring.model.Tag;
import ru.dubovitsky.flashcardsspring.repository.TagRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Set<Tag> createTagCollection(Set<Tag> tags) {
        return tags.stream().map(tag -> {
            if (tagRepository.findByName(tag.getName()).isPresent()) {
                return tag;
            } else {
                log.info(String.format("New tag with name %s saved!", tag.getName()));
                return tagRepository.save(tag);
            }
        }).collect(Collectors.toSet());
    }
}

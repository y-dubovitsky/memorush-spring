package ru.dubovitsky.flashcardsspring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import ru.dubovitsky.flashcardsspring.model.Tag;
import ru.dubovitsky.flashcardsspring.repository.TagRepository;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@Transactional
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    //!TODO Разобраться с этим методом!
    public Set<Tag> createTagCollection(Set<Tag> tags) {
        Set<Tag> result = new HashSet<>();

        tags.forEach(tag -> {
            if (CollectionUtils.isEmpty(tagRepository.findByName(tag.getName()))) {
                result.add(tagRepository.save(tag));
                log.info(String.format("New tag with name %s saved!", tag.getName()));
            } else {
                result.add(tag);
            }
        });

        return result;
    }
}

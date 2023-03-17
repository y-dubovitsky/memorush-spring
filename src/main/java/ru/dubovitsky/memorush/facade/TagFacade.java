package ru.dubovitsky.memorush.facade;

import ru.dubovitsky.memorush.model.Tag;

import java.util.Set;
import java.util.stream.Collectors;

public class TagFacade {

    public static Set<Tag> tagRequestDtoToTagSet(Set<String> tagSet) {
        return tagSet.stream()
                .map(TagFacade::tagStringToTag)
                .collect(Collectors.toSet());
    }

    public static Tag tagStringToTag(String tagName) {
        return Tag.builder()
                .name(tagName)
                .build();
    }

}

package ru.dubovitsky.flashcardsspring.facade;

import ru.dubovitsky.flashcardsspring.model.Tag;

import java.util.Set;
import java.util.stream.Collectors;

public class TagFacade {

    private static final String splitter = ",";

    public static Set<Tag> tagRequestStringToTagsList(String tagsString) {
        Set<String> tagsNameListString = Set.of(
                tagsString
                        .replaceAll("\\s+", "")
                        .split(splitter)
        );

        return tagsNameListString.stream()
                .map(tagName -> Tag.builder().name(tagName).build())
                .collect(Collectors.toSet());
    }

}

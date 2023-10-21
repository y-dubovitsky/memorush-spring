package ru.dubovitsky.memorush.facade;

import ru.dubovitsky.memorush.dto.bidirectional.DictionaryDto;
import ru.dubovitsky.memorush.model.Dictionary;
import ru.dubovitsky.memorush.model.DictionaryItem;

import java.util.stream.Collectors;

public class DictionaryFacade {

    //TODO Вынести в отдельный сервис и переработать это
    public static Dictionary dictionaryDtoToDictionary(DictionaryDto map) {
        return Dictionary.builder()
                .name("initial")
                .dictionaryItems(map.dictionaryItems.stream().map(it -> {
                    return DictionaryItem.builder()
                            .ru(it.ru)
                            .en(it.en)
                            .tr(it.tr)
                            .build();
                }).collect(Collectors.toList())).build();
    }
}
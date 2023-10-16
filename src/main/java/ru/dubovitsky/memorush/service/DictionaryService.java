package ru.dubovitsky.memorush.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dubovitsky.memorush.model.Dictionary;
import ru.dubovitsky.memorush.repository.DictionaryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DictionaryService {

    private final DictionaryRepository dictionaryRepository;

    public List<Dictionary> getAllDictionaries() {
        return dictionaryRepository.findAll();
    }

    public List<String> getAllDictionaryNames() {
        return dictionaryRepository.findAllNames().orElseThrow(
                () -> new RuntimeException("There are no dictionaries with this name")
        );
    }

    public Dictionary getByName(String name) {
        return dictionaryRepository.findByName(name).orElseThrow(
                () -> new RuntimeException("Dictionary not exist with name: " + name));
    }

    public void create(Dictionary dictionary) {
        dictionaryRepository.save(dictionary);
    }
}

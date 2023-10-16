package ru.dubovitsky.memorush.api;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dubovitsky.memorush.model.Dictionary;
import ru.dubovitsky.memorush.service.DictionaryService;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/dictionary")
public class DictionaryController {

    private final DictionaryService dictionaryService;

    @GetMapping
    public ResponseEntity<List<Dictionary>> getAllDictionaries() {
        return ResponseEntity.ok(dictionaryService.getAllDictionaries());
    }

    @GetMapping("/names")
    public ResponseEntity<?> getAllDictionariesName() {
        List<String> names = dictionaryService.getAllDictionaryNames();
        return ResponseEntity.ok(names);
    }

    @GetMapping("/by-name")
    public ResponseEntity<?> getDictionaryByName(@RequestParam String name) {
        Dictionary dictionary = dictionaryService.getByName(name);
        return ResponseEntity.ok(dictionary);
    }
}

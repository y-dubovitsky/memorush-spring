package ru.dubovitsky.flashcardsspring.api;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dubovitsky.flashcardsspring.dto.CardSetDto;
import ru.dubovitsky.flashcardsspring.model.CardSet;
import ru.dubovitsky.flashcardsspring.service.CardSetService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/card-set")
@AllArgsConstructor
public class CardSetController {

    private CardSetService cardSetService;

    @GetMapping("/all")
    public ResponseEntity<List<CardSet>> getAllCardSet() {
        List<CardSet> allCardSet = cardSetService.getAllCardSets();
        return new ResponseEntity<>(allCardSet, HttpStatus.OK);
    }

    @PostMapping("/add")
    public HttpStatus addCardSet(@RequestBody CardSetDto cardSetDto) {
        cardSetService.saveCardSet(cardSetDto);
        return HttpStatus.OK;
    }

}

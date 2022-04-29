package ru.dubovitsky.flashcardsspring.api;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.dubovitsky.flashcardsspring.model.Card;
import ru.dubovitsky.flashcardsspring.model.CardSet;
import ru.dubovitsky.flashcardsspring.service.CardService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/card")
@AllArgsConstructor
public class CardController {

    private final CardService cardService;

    @GetMapping("/all")
    public ResponseEntity<List<Card>> getAllCard() {
        return ResponseEntity.ok(cardService.getAllCard());
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> addCard(@RequestBody Card card) {
        Card savedCard = cardService.addCard(card);
        return new ResponseEntity<>(savedCard, HttpStatus.CREATED);
    }

    @GetMapping("/card-sets")
    public ResponseEntity<List<CardSet>> getAllCardSet() {
        return null;
    }
}

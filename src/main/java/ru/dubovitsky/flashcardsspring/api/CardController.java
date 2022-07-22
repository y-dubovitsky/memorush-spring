package ru.dubovitsky.flashcardsspring.api;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dubovitsky.flashcardsspring.model.Card;
import ru.dubovitsky.flashcardsspring.service.CardService;

@RestController
@RequestMapping("/api/v1/card")
@AllArgsConstructor
public class CardController {

    private final CardService cardService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> addCard(@RequestBody Card card) {
        Card savedCard = cardService.addCard(card);
        return new ResponseEntity<>(savedCard, HttpStatus.CREATED);
    }

}

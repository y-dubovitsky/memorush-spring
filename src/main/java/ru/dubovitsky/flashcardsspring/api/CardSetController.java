package ru.dubovitsky.flashcardsspring.api;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.dubovitsky.flashcardsspring.dto.request.CardSetRequestDto;
import ru.dubovitsky.flashcardsspring.model.CardSet;
import ru.dubovitsky.flashcardsspring.service.CardSetService;

import java.util.List;
import java.util.Objects;

@Slf4j
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/card-set")
@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'SUPER_ADMIN', 'OWNER')")
public class CardSetController {

    private CardSetService cardSetService;

    @GetMapping("/all")
    public ResponseEntity<List<CardSet>> getAllCardSet() {
        List<CardSet> allCardSet = cardSetService.getAllCardSets();
        return new ResponseEntity<>(allCardSet, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCardSet(@RequestBody CardSetRequestDto cardSetRequestDto) {
        var saved = cardSetService.saveCardSet(cardSetRequestDto);
        if (Objects.isNull(saved)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateEmployee(
            @PathVariable Long id,
            @RequestBody CardSetRequestDto cardSetRequestDto
    ) {
        var saved = cardSetService.updateCardSetById(id, cardSetRequestDto);
        if (Objects.isNull(saved)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> deleteCardSet(@PathVariable Long id) {
        var isRemoved = cardSetService.deleteCardSetById(id);
        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}

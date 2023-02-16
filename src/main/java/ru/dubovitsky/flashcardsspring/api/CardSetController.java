package ru.dubovitsky.flashcardsspring.api;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.dubovitsky.flashcardsspring.dto.request.CardSetRequestDto;
import ru.dubovitsky.flashcardsspring.dto.response.CardSetResponseDto;
import ru.dubovitsky.flashcardsspring.facade.CardSetFacade;
import ru.dubovitsky.flashcardsspring.model.CardSet;
import ru.dubovitsky.flashcardsspring.service.CardSetService;

import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/card-set")
@PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'SUPER_ADMIN', 'OWNER')")
public class CardSetController {

    private CardSetService cardSetService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllUserCardSets(Principal principal) {
        Set<CardSet> cardSet = cardSetService.getAllUserCardSetsList(principal).orElseThrow(
                () -> new RuntimeException("There is no card set")
        );
        Set<CardSetResponseDto> response = cardSet.stream()
                .map(set -> CardSetFacade.cardSetToCardSetResponseDto(set))
                .collect(Collectors.toSet());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getCardSetBySearchParam(@RequestParam("search") String search) {
        List<CardSet> cardSets = cardSetService.searchAllCardSetsBySearchString(search);
        Set<CardSetResponseDto> response = cardSets.stream()
                .map(set -> CardSetFacade.cardSetToCardSetResponseDto(set))
                .collect(Collectors.toSet());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCardSet(
            @RequestBody CardSetRequestDto cardSetRequestDto,
            Principal principal
    ) {
        var saved = cardSetService.createCardSet(cardSetRequestDto, principal);
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
            return new ResponseEntity<>(String.format("CardSet not exist with id: %s", id), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(CardSetFacade.cardSetToCardSetResponseDto(saved));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCardSet(@PathVariable Long id) {
        var isRemoved = cardSetService.deleteCardSetById(id);
        if (!isRemoved) {
            return new ResponseEntity<>(String.format("CardSet with id: %s didn't deleted ", id), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}

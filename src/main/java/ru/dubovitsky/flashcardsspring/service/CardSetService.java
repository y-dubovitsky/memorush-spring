package ru.dubovitsky.flashcardsspring.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import ru.dubovitsky.flashcardsspring.dto.request.CardSetRequestDto;
import ru.dubovitsky.flashcardsspring.facade.CardFacade;
import ru.dubovitsky.flashcardsspring.facade.CardSetFacade;
import ru.dubovitsky.flashcardsspring.model.CardSet;
import ru.dubovitsky.flashcardsspring.model.User;
import ru.dubovitsky.flashcardsspring.repository.CardSetRepository;

import java.security.Principal;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class CardSetService {

    private CardSetRepository cardSetRepository;
    private UserService userService;

    public CardSet saveCardSet(CardSetRequestDto cardSetRequestDto) {
        CardSet savedCardSet = cardSetRepository.save(CardSetFacade.cardSetRequestDtoToCardSet(cardSetRequestDto));
        log.info(String.format("CardSet with id %s saved", savedCardSet.getId()));
        return savedCardSet;
    }

    public Optional<Set<CardSet>> getAllUserCardSets(Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        return cardSetRepository.findAllByUser(user);
    }

    public boolean deleteCardSetById(Long id) {
        boolean present = cardSetRepository.findById(id).isPresent();
        if(!present) {
            log.info(String.format("CardSet with id %s not found!", id));
            return false;
        }
        cardSetRepository.deleteById(id);
        log.info(String.format("CardSet with id %s deleted", id));
        return true;
    }

    public CardSet updateCardSetById(Long id, CardSetRequestDto cardSetRequestDto) {
        CardSet cardSet = cardSetRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("CardSet not exist with id: " + id));

        cardSet.setName(cardSetRequestDto.getTitle());
        cardSet.setDescription(cardSetRequestDto.getDescription());
        cardSet.setTags(cardSetRequestDto.getTags());
        cardSet.setCardList(cardSetRequestDto.getFlashCardArray()
                .stream()
                .map(cardDto -> CardFacade.cardRequestDtoToCard(cardDto))
                .collect(Collectors.toList()));

        cardSetRepository.save(cardSet);
        log.info(String.format("Card set with id: %s updated", id));
        return cardSet;
    }
}

package ru.dubovitsky.flashcardsspring.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dubovitsky.flashcardsspring.dto.request.CardSetRequestDto;
import ru.dubovitsky.flashcardsspring.facade.CardFacade;
import ru.dubovitsky.flashcardsspring.facade.CardSetFacade;
import ru.dubovitsky.flashcardsspring.model.CardSet;
import ru.dubovitsky.flashcardsspring.model.User;
import ru.dubovitsky.flashcardsspring.repository.CardSetRepository;

import java.security.Principal;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class CardSetService {

    private CardSetRepository cardSetRepository;
    private UserService userService;

    public Optional<Collection<CardSet>> getAllCardSets() {
        return Optional.of(cardSetRepository.findAll());
    }

    public CardSet createCardSet(
            CardSetRequestDto cardSetRequestDto,
            Principal principal
    ) {
        User user = userService.getCurrentUserByPrincipal(principal);
        CardSet cardSet = CardSetFacade.cardSetRequestDtoToCardSet(cardSetRequestDto);
        cardSet.setUser(user);
        CardSet savedCardSet = cardSetRepository.save(cardSet);
        log.info(String.format("CardSet with id %s saved", savedCardSet.getId()));
        return savedCardSet;
    }

    public Optional<Set<CardSet>> getAllUserCardSetsList(Principal principal) {
        User user = userService.getCurrentUserByPrincipal(principal);
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
                .collect(Collectors.toSet()));

        CardSet updatedCardSet = cardSetRepository.save(cardSet);
        log.info(String.format("Card set with id: %s updated", id));
        return updatedCardSet;
    }
}

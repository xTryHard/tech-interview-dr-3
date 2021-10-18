package com.cardlay.techinterview.cardmanagement.service;

import com.cardlay.techinterview.cardmanagement.entity.Card;
import com.cardlay.techinterview.cardmanagement.entity.CardHolder;
import com.cardlay.techinterview.cardmanagement.exception.NotFoundException;
import com.cardlay.techinterview.cardmanagement.repository.CardHolderRepository;
import com.cardlay.techinterview.cardmanagement.repository.CardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class CardHolderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CardHolderService.class);

    private final CardHolderRepository cardHolderRepository;
    private CardRepository cardRepository;

    public CardHolderService(CardHolderRepository cardHolderRepository, CardRepository cardRepository) {
        this.cardHolderRepository = cardHolderRepository;
        this.cardRepository = cardRepository;
    }

    public List<CardHolder> getAllCardHolders() {
        Iterable<CardHolder> cardHolderIterable = cardHolderRepository.findAll();

        return StreamSupport.stream(cardHolderIterable.spliterator(), false)
                .toList();
    }

@Transactional
    public CardHolder createCardHolder(String name, String email,List<CardDto> cards) {
        CardHolder cardHolder = new CardHolder();
        cardHolder.setName(name);
        cardHolder.setEmail(email);

        cardHolderRepository.save(cardHolder);

        cards.forEach(card -> {
            Card cardEntity = new Card();
            cardEntity.setCardHolder(cardHolder);
            cardEntity.setBalance(card.getBlance());
            cardEntity.setCardNumber(card.getCardNumber());
            cardRepository.save(cardEntity);
            LOGGER.info("Card created with PAN {} and balance of {}", card.getCardNumber(), card.getBlance());
        });

        return cardHolder;
    }

    @Transactional
    public CardHolder updateCardHolder(Long cardHolderId, String name) {
        Optional<CardHolder> cardHolderOptional = cardHolderRepository.findById(cardHolderId);
        if (cardHolderOptional.isEmpty()) {
            throw new NotFoundException();
        }

        CardHolder cardHolder = cardHolderOptional.get();

        if (name != null) {
            cardHolder.setName(name);
        }

        return cardHolderRepository.save(cardHolder);
    }
}

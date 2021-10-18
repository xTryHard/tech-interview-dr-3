package com.cardlay.techinterview.cardmanagement.service;

import com.cardlay.techinterview.cardmanagement.entity.Card;
import com.cardlay.techinterview.cardmanagement.entity.CardHolder;
import com.cardlay.techinterview.cardmanagement.exception.NotFoundException;
import com.cardlay.techinterview.cardmanagement.repository.CardHolderRepository;
import com.cardlay.techinterview.cardmanagement.repository.CardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final CardHolderRepository cardHolderRepository;

    public CardService(CardRepository cardRepository, CardHolderRepository cardHolderRepository) {
        this.cardRepository = cardRepository;
        this.cardHolderRepository = cardHolderRepository;
    }

    @Transactional(readOnly = true)
    public List<Card> getAllCardsForCardHolder(Long cardHolderId) {
        if (!cardHolderRepository.existsById(cardHolderId)) {
            throw new NotFoundException();
        }

        return cardRepository.findAllByCardHolderId(cardHolderId);
    }

    @Transactional
    public Card addToCardBalance(Long cardId, BigDecimal amount) {
        Optional<Card> cardOptional = cardRepository.findById(cardId);
        if (cardOptional.isEmpty()) {
            throw new NotFoundException();
        }

        Card card = cardOptional.get();
        card.setBalance(card.getBalance().add(amount));

        return cardRepository.save(card);
    }

    @Transactional
    public Card createCardForCardHolder(Long cardHolderId, String cardNumber, BigDecimal balance) {
        Optional<CardHolder> cardHolderOptional = cardHolderRepository.findById(cardHolderId);
        if (cardHolderOptional.isEmpty()) {
            throw new NotFoundException();
        }

        CardHolder cardHolder = cardHolderOptional.get();

        Card card = new Card();
        card.setCardNumber(cardNumber);
        card.setBalance(balance);
        card.setCardHolder(cardHolder);

        return cardRepository.save(card);
    }
}

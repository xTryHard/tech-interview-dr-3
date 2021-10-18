package com.cardlay.techinterview.cardmanagement.service;

import com.cardlay.techinterview.cardmanagement.entity.CardHolder;
import com.cardlay.techinterview.cardmanagement.exception.NotFoundException;
import com.cardlay.techinterview.cardmanagement.repository.CardHolderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class CardHolderService {

    private final CardHolderRepository cardHolderRepository;

    public CardHolderService(CardHolderRepository cardHolderRepository) {
        this.cardHolderRepository = cardHolderRepository;
    }

    public List<CardHolder> getAllCardHolders() {
        Iterable<CardHolder> cardHolderIterable = cardHolderRepository.findAll();

        return StreamSupport.stream(cardHolderIterable.spliterator(), false)
                .toList();
    }

    public CardHolder createCardHolder(String name, String email) {
        CardHolder cardHolder = new CardHolder();
        cardHolder.setName(name);
        cardHolder.setEmail(email);

        return cardHolderRepository.save(cardHolder);
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

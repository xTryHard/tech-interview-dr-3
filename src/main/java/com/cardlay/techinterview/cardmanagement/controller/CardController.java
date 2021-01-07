package com.cardlay.techinterview.cardmanagement.controller;

import com.cardlay.techinterview.cardmanagement.controller.request.CreateCardDto;
import com.cardlay.techinterview.cardmanagement.controller.request.UpdateCardBalanceDto;
import com.cardlay.techinterview.cardmanagement.controller.response.CardResponseDto;
import com.cardlay.techinterview.cardmanagement.entity.Card;
import com.cardlay.techinterview.cardmanagement.service.CardService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public List<CardResponseDto> getAllCards(
            @RequestParam("cardHolderId") Long cardHolderId
    ) {
        List<Card> cards = cardService.getAllCardsForCardHolder(cardHolderId);

        return cards.stream()
                .map(CardResponseDto::assembleFromCard)
                .collect(Collectors.toList());
    }

    @PostMapping
    public CardResponseDto createCardForCardHolder(
            @RequestParam("cardHolderId") Long cardHolderId,
            @RequestBody @Validated CreateCardDto createCardHolderDto
    ) {
        Card card = cardService.createCardForCardHolder(
                cardHolderId,
                createCardHolderDto.getCardNumber(),
                createCardHolderDto.getBalance()
        );

        return CardResponseDto.assembleFromCard(card, true);
    }

    @PostMapping("/{cardId}/insert")
    public CardResponseDto updateCardBalance(
            @PathVariable("cardId") Long cardId,
            @RequestBody @Validated UpdateCardBalanceDto updateCardBalanceDto
    ) {
        Card card = cardService.addToCardBalance(cardId, updateCardBalanceDto.getAmount());

        return CardResponseDto.assembleFromCard(card);
    }

}

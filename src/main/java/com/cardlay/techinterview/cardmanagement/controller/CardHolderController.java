package com.cardlay.techinterview.cardmanagement.controller;

import com.cardlay.techinterview.cardmanagement.controller.request.CreateCardHolderDto;
import com.cardlay.techinterview.cardmanagement.controller.request.UpdateCardHolderDto;
import com.cardlay.techinterview.cardmanagement.controller.response.CardHolderResponseDto;
import com.cardlay.techinterview.cardmanagement.entity.CardHolder;
import com.cardlay.techinterview.cardmanagement.service.CardHolderService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cardHolders")
public class CardHolderController {

    private final CardHolderService cardHolderService;

    public CardHolderController(CardHolderService cardHolderService) {
        this.cardHolderService = cardHolderService;
    }

    @GetMapping
    public List<CardHolderResponseDto> getCardHolders() {
        List<CardHolder> cardHolders = cardHolderService.getAllCardHolders();

        return cardHolders.stream()
                .map(CardHolderResponseDto::assembleFromCardHolder)
                .toList();
    }

    @PostMapping
    public CardHolderResponseDto createCardHolder(@RequestBody @Validated CreateCardHolderDto cardHolderDto) {
        CardHolder cardHolder = cardHolderService.createCardHolder(cardHolderDto.name(), cardHolderDto.email());

        return CardHolderResponseDto.assembleFromCardHolder(cardHolder);
    }

    @PatchMapping("/{cardHolderId}")
    public CardHolderResponseDto updateCardHolder(
            @PathVariable("cardHolderId") Long cardHolderId,
            @RequestBody @Validated UpdateCardHolderDto updateCardHolderDto
    ) {
        CardHolder cardHolder = cardHolderService.updateCardHolder(cardHolderId, updateCardHolderDto.name());

        return CardHolderResponseDto.assembleFromCardHolder(cardHolder);
    }

}

package com.cardlay.techinterview.cardmanagement.controller;

import com.cardlay.techinterview.cardmanagement.controller.request.CreateCardHolderDto;
import com.cardlay.techinterview.cardmanagement.controller.request.UpdateCardHolderDto;
import com.cardlay.techinterview.cardmanagement.controller.response.CardHolderResponseDto;
import com.cardlay.techinterview.cardmanagement.entity.CardHolder;
import com.cardlay.techinterview.cardmanagement.exception.BadRequestException;
import com.cardlay.techinterview.cardmanagement.repository.CardHolderRepository;
import com.cardlay.techinterview.cardmanagement.service.CardDto;
import com.cardlay.techinterview.cardmanagement.service.CardHolderService;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cardHolders")
public class CardHolderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CardHolderController.class);

    private final CardHolderService cardHolderService;
    private final CardHolderRepository cardHolderRepository;

    public CardHolderController(CardHolderService cardHolderService, CardHolderRepository cardHolderRepository) {
        this.cardHolderService = cardHolderService;
        this.cardHolderRepository = cardHolderRepository;
    }

    @GetMapping
    public List<CardHolderResponseDto> getCardHolders() {
        List<CardHolder> cardHolders = cardHolderService.getAllCardHolders();

        return cardHolders.stream()
                .map(CardHolderResponseDto::assembleFromCardHolder)
                .toList();
    }

    @PostMapping
    public CardHolderResponseDto createCardHolder(@RequestBody @Validated CreateCardHolderDto cardHolderDto)
            throws BadRequestException {

        CardHolder cardHolder = cardHolderRepository.findByEmail(cardHolderDto.email());

        if(cardHolder == null){
            LOGGER.info("Creating cardholder");
            cardHolder = cardHolderService.createCardHolder(cardHolderDto.name(), cardHolderDto.email()
                    , cardHolderDto.cards().stream().map(createCardDto -> new CardDto(createCardDto.cardNumber(),
                            createCardDto.balance())).collect(Collectors.toList()));

            return CardHolderResponseDto.assembleFromCardHolder(cardHolder);
        }else {
            LOGGER.error("Cardholder with email " + cardHolder.getEmail() + " already exists");
            throw new BadRequestException("Cardholder with email " + cardHolder.getEmail() + " already exists");
        }
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

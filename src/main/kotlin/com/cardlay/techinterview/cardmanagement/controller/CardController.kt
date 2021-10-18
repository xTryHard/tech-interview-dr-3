package com.cardlay.techinterview.cardmanagement.controller

import com.cardlay.techinterview.cardmanagement.controller.request.CreateCardDto
import com.cardlay.techinterview.cardmanagement.controller.request.UpdateCardBalanceDto
import com.cardlay.techinterview.cardmanagement.controller.response.CardResponseDto
import com.cardlay.techinterview.cardmanagement.service.CardService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/cards")
class CardController(
    private val cardService: CardService
) {
    @GetMapping
    fun getAllCards(
        @RequestParam("cardHolderId") cardHolderId: Long
    ): List<CardResponseDto> {
        val cards = cardService.getAllCardsForCardHolder(cardHolderId)

        return cards.map(CardResponseDto::assembleFromCard)
    }

    @PostMapping
    fun createCardForCardHolder(
        @RequestParam("cardHolderId") cardHolderId: Long,
        @RequestBody @Validated createCardHolderDto: CreateCardDto
    ): CardResponseDto {
        val card = cardService.createCardForCardHolder(
            cardHolderId,
            createCardHolderDto.cardNumber,
            createCardHolderDto.balance
        )

        return CardResponseDto.assembleFromCard(card, true)
    }

    @PostMapping("/{cardId}/insert")
    fun updateCardBalance(
        @PathVariable("cardId") cardId: Long,
        @RequestBody @Validated updateCardBalanceDto: UpdateCardBalanceDto
    ): CardResponseDto {
        val card = cardService.addToCardBalance(cardId, updateCardBalanceDto.amount)

        return CardResponseDto.assembleFromCard(card)
    }
}
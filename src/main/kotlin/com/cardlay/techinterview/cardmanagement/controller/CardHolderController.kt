package com.cardlay.techinterview.cardmanagement.controller

import com.cardlay.techinterview.cardmanagement.controller.request.CreateCardHolderDto
import com.cardlay.techinterview.cardmanagement.controller.request.UpdateCardHolderDto
import com.cardlay.techinterview.cardmanagement.controller.response.CardHolderResponseDto
import com.cardlay.techinterview.cardmanagement.service.CardHolderService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/cardHolders")
class CardHolderController(
    private val cardHolderService: CardHolderService
) {
    @GetMapping
    fun getAllCardhHolders(): List<CardHolderResponseDto> {
        val cardHolders = cardHolderService.getAllCardHolders()

        return cardHolders.map(CardHolderResponseDto::assembleFromCardHolder)
    }

    @PostMapping
    fun createCardHolder(@RequestBody @Validated cardHolderDto: CreateCardHolderDto): CardHolderResponseDto {
        val cardHolder = cardHolderService.createCardHolder(cardHolderDto.name, cardHolderDto.email)

        return CardHolderResponseDto.assembleFromCardHolder(cardHolder)
    }

    @PatchMapping("/{cardHolderId}")
    fun updateCardHolder(
        @PathVariable("cardHolderId") cardHolderId: Long,
        @RequestBody @Validated updateCardHolderDto: UpdateCardHolderDto
    ): CardHolderResponseDto {
        val cardHolder = cardHolderService.updateCardHolder(cardHolderId, updateCardHolderDto.name)

        return CardHolderResponseDto.assembleFromCardHolder(cardHolder)
    }
}
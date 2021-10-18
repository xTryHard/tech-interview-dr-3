package com.cardlay.techinterview.cardmanagement.controller.response

import com.cardlay.techinterview.cardmanagement.entity.Card
import com.fasterxml.jackson.annotation.JsonInclude
import java.math.BigDecimal

data class CardResponseDto(
    val id: Long,
    val cardNumber: String,
    val balance: BigDecimal,
    val cardHolderId: Long,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val cardHolder: CardHolderResponseDto?,
) {
    companion object {
        fun assembleFromCard(card: Card): CardResponseDto {
            return assembleFromCard(card, false)
        }

        fun assembleFromCard(card: Card, includeCardHolder: Boolean): CardResponseDto {
            var cardHolderResponseDto: CardHolderResponseDto? = null
            if (includeCardHolder && card.cardHolder != null) {
                cardHolderResponseDto = CardHolderResponseDto.assembleFromCardHolder(card.cardHolder)
            }

            return CardResponseDto(
                card.id,
                card.cardNumber,
                card.balance,
                card.cardHolder.id,
                cardHolderResponseDto
            )
        }
    }
}
package com.cardlay.techinterview.cardmanagement.controller.response

import com.cardlay.techinterview.cardmanagement.entity.CardHolder

data class CardHolderResponseDto(
    val id: Long,
    val name: String,
    val email: String,
) {
    companion object {
        fun assembleFromCardHolder(cardHolder: CardHolder): CardHolderResponseDto {
            return CardHolderResponseDto(
                cardHolder.id,
                cardHolder.name,
                cardHolder.email
            )
        }
    }
}
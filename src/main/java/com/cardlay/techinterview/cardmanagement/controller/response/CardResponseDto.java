package com.cardlay.techinterview.cardmanagement.controller.response;

import com.cardlay.techinterview.cardmanagement.entity.Card;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;

public record CardResponseDto(
    Long id,
    String cardNumber,
    BigDecimal balance,
    Long cardHolderId,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    CardHolderResponseDto cardHolder
) {
    public static CardResponseDto assembleFromCard(Card card) {
        return CardResponseDto.assembleFromCard(card, false);
    }

    public static CardResponseDto assembleFromCard(Card card, boolean includeCardHolder) {
        CardHolderResponseDto cardHolderResponseDto = null;
        if (includeCardHolder && card.getCardHolder() != null) {
            cardHolderResponseDto = CardHolderResponseDto.assembleFromCardHolder(card.getCardHolder());
        }

        return new CardResponseDto(
            card.getId(),
            card.getCardNumber(),
            card.getBalance(),
            card.getCardHolder().getId(),
            cardHolderResponseDto
        );
    }
}

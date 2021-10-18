package com.cardlay.techinterview.cardmanagement.controller.response;

import com.cardlay.techinterview.cardmanagement.entity.CardHolder;

public record CardHolderResponseDto(
    Long id,
    String name,
    String email
) {
    public static CardHolderResponseDto assembleFromCardHolder(CardHolder cardHolder) {
        return new CardHolderResponseDto(
            cardHolder.getId(),
            cardHolder.getName(),
            cardHolder.getEmail()
        );
    }
}

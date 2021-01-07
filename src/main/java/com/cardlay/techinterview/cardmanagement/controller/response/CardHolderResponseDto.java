package com.cardlay.techinterview.cardmanagement.controller.response;

import com.cardlay.techinterview.cardmanagement.entity.CardHolder;

public class CardHolderResponseDto {
    private Long id;
    private String name;
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static CardHolderResponseDto assembleFromCardHolder(CardHolder cardHolder) {
        CardHolderResponseDto cardHolderResponseDto = new CardHolderResponseDto();
        cardHolderResponseDto.setId(cardHolder.getId());
        cardHolderResponseDto.setName(cardHolder.getName());
        cardHolderResponseDto.setEmail(cardHolder.getEmail());

        return cardHolderResponseDto;
    }
}

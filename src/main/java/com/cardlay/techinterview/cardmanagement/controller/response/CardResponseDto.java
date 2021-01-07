package com.cardlay.techinterview.cardmanagement.controller.response;

import com.cardlay.techinterview.cardmanagement.entity.Card;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;

public class CardResponseDto {
    private Long id;
    private String cardNumber;
    private BigDecimal balance;
    private Long cardHolderId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CardHolderResponseDto cardHolder;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Long getCardHolderId() {
        return cardHolderId;
    }

    public void setCardHolderId(Long cardHolderId) {
        this.cardHolderId = cardHolderId;
    }

    public CardHolderResponseDto getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(CardHolderResponseDto cardHolder) {
        this.cardHolder = cardHolder;
    }

    public static CardResponseDto assembleFromCard(Card card) {
        return CardResponseDto.assembleFromCard(card, false);
    }

    public static CardResponseDto assembleFromCard(Card card, boolean includeCardHolder) {
        CardResponseDto cardResponseDto = new CardResponseDto();
        cardResponseDto.setId(card.getId());
        cardResponseDto.setCardNumber(card.getCardNumber());
        cardResponseDto.setBalance(card.getBalance());
        cardResponseDto.setCardHolderId(card.getCardHolder().getId());

        if (includeCardHolder && card.getCardHolder() != null) {
            cardResponseDto.setCardHolder(CardHolderResponseDto.assembleFromCardHolder(card.getCardHolder()));
        }

        return cardResponseDto;
    }
}

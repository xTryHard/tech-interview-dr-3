package com.cardlay.techinterview.cardmanagement.service;

import java.math.BigDecimal;

public class CardDto {

    private String cardNumber;
    private BigDecimal blance;

    public CardDto(String cardNumber, BigDecimal blance) {
        this.cardNumber = cardNumber;
        this.blance = blance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public BigDecimal getBlance() {
        return blance;
    }

    public void setBlance(BigDecimal blance) {
        this.blance = blance;
    }
}

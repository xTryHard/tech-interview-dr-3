package com.cardlay.techinterview.cardmanagement.controller.request;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

public class UpdateCardBalanceDto {

    @Min(0)
    private BigDecimal amount;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}

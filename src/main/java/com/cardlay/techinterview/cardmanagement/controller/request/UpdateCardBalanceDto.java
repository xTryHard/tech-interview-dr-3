package com.cardlay.techinterview.cardmanagement.controller.request;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

public record UpdateCardBalanceDto(
    @Min(0)
    BigDecimal amount
) {
}

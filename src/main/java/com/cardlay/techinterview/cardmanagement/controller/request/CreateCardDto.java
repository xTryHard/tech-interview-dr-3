package com.cardlay.techinterview.cardmanagement.controller.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public record CreateCardDto(
    @NotNull
    @NotBlank
    String cardNumber,

    @Min(0)
    BigDecimal balance
) {
}

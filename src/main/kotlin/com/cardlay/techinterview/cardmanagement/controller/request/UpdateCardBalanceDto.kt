package com.cardlay.techinterview.cardmanagement.controller.request

import java.math.BigDecimal
import javax.validation.constraints.Min

data class UpdateCardBalanceDto(
    @field:Min(0)
    val amount: BigDecimal,
)
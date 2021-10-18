package com.cardlay.techinterview.cardmanagement.controller.request

import java.math.BigDecimal
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CreateCardDto(
    @field:NotNull
    @field:NotBlank
    val cardNumber: String,

    @field:Min(0)
    val balance: BigDecimal,
)
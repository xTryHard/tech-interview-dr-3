package com.cardlay.techinterview.cardmanagement.controller.request

import org.hibernate.validator.constraints.Length

data class UpdateCardHolderDto(
    @field:Length(min = 1)
    val name: String,
)
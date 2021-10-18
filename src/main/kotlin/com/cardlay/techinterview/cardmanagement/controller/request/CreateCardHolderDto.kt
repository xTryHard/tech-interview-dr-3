package com.cardlay.techinterview.cardmanagement.controller.request

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CreateCardHolderDto(
    @field:NotNull
    @field:NotBlank
    val name: String,

    @field:NotNull
    @field:NotBlank
    @field:Email
    val email: String,
)
package com.cardlay.techinterview.cardmanagement.controller.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record CreateCardHolderDto(
    @NotNull
    @NotBlank
    String name,

    @NotNull
    @NotBlank
    @Email
    String email
) {
}

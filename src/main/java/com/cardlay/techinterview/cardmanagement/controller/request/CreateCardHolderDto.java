package com.cardlay.techinterview.cardmanagement.controller.request;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public record CreateCardHolderDto(
    @NotNull
    @NotBlank
    String name,

    @NotNull
    @NotBlank
    @Email
    String email,

    @Valid
    List<CreateCardDto> cards
) {
}

package com.cardlay.techinterview.cardmanagement.controller.request;

import org.hibernate.validator.constraints.Length;

public record UpdateCardHolderDto(
    @Length(min = 1)
    String name
) {
}

package com.cardlay.techinterview.cardmanagement.controller.request;

import org.hibernate.validator.constraints.Length;

public class UpdateCardHolderDto {

    @Length(min = 1)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

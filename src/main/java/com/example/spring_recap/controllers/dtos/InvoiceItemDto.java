package com.example.spring_recap.controllers.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record InvoiceItemDto(Long id,
                             @NotEmpty String name,
                             @NotNull @Positive Double price,
                             @NotNull @Positive Integer quantity) {
}

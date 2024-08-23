package com.example.spring_recap.controllers.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record InvoiceItemDto(Long id,
                             @NotEmpty String name,
                             @NotNull @Positive Double price,
                             @NotNull @Positive Integer quantity) {
}

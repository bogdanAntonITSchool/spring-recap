package com.example.spring_recap.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class InvoiceDto {

    private Long id;

//    @JsonIgnore
    private LocalDateTime date;

//    @JsonIgnore
    private Double total;

    @Valid
    @NotNull
    @Size(min = 1, max = 5)
    private List<@NotNull InvoiceItemDto> items;
}

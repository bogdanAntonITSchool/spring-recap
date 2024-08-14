package com.example.spring_recap.controllers.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InvoiceDto {

    private Long id;

    private LocalDateTime date;

    private Double total;
}

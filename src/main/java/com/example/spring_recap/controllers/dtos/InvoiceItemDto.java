package com.example.spring_recap.controllers.dtos;

public record InvoiceItemDto(Long id, String name, Double price, Integer quantity) {
}

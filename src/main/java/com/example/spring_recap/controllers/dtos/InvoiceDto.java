package com.example.spring_recap.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private List<InvoiceItemDto> items;
}

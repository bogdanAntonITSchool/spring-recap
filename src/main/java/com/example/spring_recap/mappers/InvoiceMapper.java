package com.example.spring_recap.mappers;

import com.example.spring_recap.controllers.dtos.InvoiceDto;
import com.example.spring_recap.controllers.dtos.InvoiceItemDto;
import com.example.spring_recap.persitence.entities.Invoice;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.util.List;


@UtilityClass
public class InvoiceMapper {

    public InvoiceDto toDto(Invoice invoice) {
        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.setId(invoice.getId());
        invoiceDto.setDate(invoice.getDate());
        invoiceDto.setTotal(invoice.getTotal());
        invoiceDto.setItems(invoice.getItems().stream()
                .map(InvoiceItemMapper::toDto)
                .toList());
        return invoiceDto;
    }

    public Invoice toEntity(InvoiceDto invoiceDto) {
        Invoice invoice = new Invoice();
        invoice.setId(invoiceDto.getId());
        invoice.setDate(LocalDateTime.now());
        invoice.setTotal(calculateTotal(invoiceDto.getItems()));
        invoice.setItems(invoiceDto.getItems().stream()
                .map(item -> InvoiceItemMapper.toEntity(item, invoice))
                .toList());
        return invoice;
    }

    private static Double calculateTotal(List<InvoiceItemDto> items) {
        return items.stream()
                .mapToDouble(item -> item.price() * item.quantity())
                .sum();
    }
}

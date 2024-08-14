package com.example.spring_recap.mappers;

import com.example.spring_recap.controllers.dtos.InvoiceDto;
import com.example.spring_recap.persitence.entities.Invoice;
import lombok.experimental.UtilityClass;

@UtilityClass
public class InvoiceMapper {

    public InvoiceDto toDto(Invoice invoice) {
        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.setId(invoice.getId());
        invoiceDto.setDate(invoice.getDate());
        invoiceDto.setTotal(invoice.getTotal());
        return invoiceDto;
    }

    public Invoice toEntity(InvoiceDto invoiceDto) {
        Invoice invoice = new Invoice();
        invoice.setId(invoiceDto.getId());
        invoice.setDate(invoiceDto.getDate());
        invoice.setTotal(invoiceDto.getTotal());
        return invoice;
    }
}

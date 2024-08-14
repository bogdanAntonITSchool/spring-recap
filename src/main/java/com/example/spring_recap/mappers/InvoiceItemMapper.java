package com.example.spring_recap.mappers;

import com.example.spring_recap.controllers.dtos.InvoiceItemDto;
import com.example.spring_recap.persitence.entities.Invoice;
import com.example.spring_recap.persitence.entities.InvoiceItem;
import lombok.experimental.UtilityClass;

@UtilityClass
public class InvoiceItemMapper {

    public InvoiceItem toEntity(InvoiceItemDto invoiceItemDto, Invoice invoice) {
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setId(invoiceItemDto.id());
        invoiceItem.setName(invoiceItemDto.name());
        invoiceItem.setPrice(invoiceItemDto.price());
        invoiceItem.setQuantity(invoiceItemDto.quantity());
        invoiceItem.setInvoice(invoice);
        return invoiceItem;
    }

    public InvoiceItemDto toDto(InvoiceItem invoiceItem) {
        return new InvoiceItemDto(invoiceItem.getId(),
                invoiceItem.getName(),
                invoiceItem.getPrice(),
                invoiceItem.getQuantity());
    }
}

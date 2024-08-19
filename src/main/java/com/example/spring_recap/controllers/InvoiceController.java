package com.example.spring_recap.controllers;

import com.example.spring_recap.controllers.dtos.InvoiceDto;
import com.example.spring_recap.controllers.dtos.InvoiceItemDto;
import com.example.spring_recap.controllers.dtos.ResponsePayload;
import com.example.spring_recap.mappers.InvoiceItemMapper;
import com.example.spring_recap.mappers.InvoiceMapper;
import com.example.spring_recap.persitence.entities.Invoice;
import com.example.spring_recap.persitence.entities.InvoiceItem;
import com.example.spring_recap.services.InvoiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @GetMapping
    public ResponseEntity<List<InvoiceDto>> getAllInvoices() {
        List<Invoice> allInvoices = invoiceService.getAllInvoices();
        return ResponseEntity.ok(allInvoices.stream()
                .map(InvoiceMapper::toDto)
                .toList());
    }

    @PostMapping
    public ResponseEntity<ResponsePayload<InvoiceDto>> addInvoice(@RequestBody @Valid InvoiceDto invoiceDto) {
        Invoice invoice = invoiceService.addInvoice(InvoiceMapper.toEntity(invoiceDto));
        return new ResponseEntity<>(new ResponsePayload<>(
                InvoiceMapper.toDto(invoice),
                "Invoice added successfully"
        ), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDto> getInvoiceById(@PathVariable Long id) {
        Invoice invoice = invoiceService.getInvoiceById(id);
        return ResponseEntity.ok(InvoiceMapper.toDto(invoice));
    }

    @GetMapping("/{id}/invoice-items")
    public ResponseEntity<List<InvoiceItemDto>> getInvoiceItemsByInvoiceId(@PathVariable Long id) {
        Invoice invoiceById = invoiceService.getInvoiceById(id);
        return new ResponseEntity<>(invoiceById.getItems().stream()
                .map(InvoiceItemMapper::toDto)
                .toList(), HttpStatus.OK);
    }

    @PostMapping("/{id}/invoice-items")
    public ResponseEntity<ResponsePayload<List<InvoiceItemDto>>> addInvoiceItemToInvoice(@PathVariable Long id,
                                                                                   @RequestBody InvoiceItemDto invoiceItemDto) {
        Invoice invoiceById = invoiceService.getInvoiceById(id);
        InvoiceItem invoiceItem = InvoiceItemMapper.toEntity(invoiceItemDto, invoiceById);

        invoiceById.getItems().add(invoiceItem);
        invoiceService.addInvoice(invoiceById);

        return new ResponseEntity<>(new ResponsePayload<>(
                invoiceById.getItems().stream()
                        .map(InvoiceItemMapper::toDto)
                        .toList(),
                "Invoice item added successfully"
        ), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/invoice-items/{itemId}")
    public ResponseEntity<InvoiceItemDto> getInvoiceItemByInvoiceIdAndItemId(@PathVariable Long id,
                                                                             @PathVariable Long itemId) {
        Invoice invoiceById = invoiceService.getInvoiceById(id);
        InvoiceItem invoiceItem = invoiceById.getItems().stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Invoice item with id " + itemId + " not found"));
        return new ResponseEntity<>(InvoiceItemMapper.toDto(invoiceItem), HttpStatus.OK);
    }
}

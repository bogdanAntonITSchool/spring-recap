package com.example.spring_recap.controllers;

import com.example.spring_recap.controllers.dtos.InvoiceDto;
import com.example.spring_recap.mappers.InvoiceMapper;
import com.example.spring_recap.persitence.entities.Invoice;
import com.example.spring_recap.services.InvoiceService;
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
    public ResponseEntity<InvoiceDto> addInvoice(@RequestBody InvoiceDto invoiceDto) {
        Invoice invoice = invoiceService.addInvoice(InvoiceMapper.toEntity(invoiceDto));
        return new ResponseEntity<>(InvoiceMapper.toDto(invoice), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDto> getInvoiceById(@PathVariable Long id) {
        Invoice invoice = invoiceService.getInvoiceById(id);
        return ResponseEntity.ok(InvoiceMapper.toDto(invoice));
    }
}

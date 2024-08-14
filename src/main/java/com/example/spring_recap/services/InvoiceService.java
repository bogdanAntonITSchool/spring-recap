package com.example.spring_recap.services;

import com.example.spring_recap.exceptions.InvoiceNotFoundException;
import com.example.spring_recap.persitence.entities.Invoice;
import com.example.spring_recap.persitence.repositories.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public List<Invoice> getAllInvoices() {
        return (List<Invoice>) invoiceRepository.findAll();
    }

    public Invoice getInvoiceById(Long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new InvoiceNotFoundException("Invoice with id " + id + " not found"));
    }

    public Invoice addInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }
}

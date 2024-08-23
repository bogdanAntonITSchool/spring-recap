package com.example.spring_recap.services;

import com.example.spring_recap.exceptions.InvoiceNotFoundException;
import com.example.spring_recap.persitence.entities.Invoice;
import com.example.spring_recap.persitence.repositories.InvoiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InvoiceServiceTest {

    @Mock
    private InvoiceRepository invoiceRepository;

    @InjectMocks
    private InvoiceService invoiceService;

    @Test
    void getAllInvoices() {
        // Given
        LocalDateTime time = LocalDateTime.now();

        Invoice invoice = new Invoice();
        invoice.setId(1L);
        invoice.setDate(time);
        invoice.setTotal(100.0);

        List<Invoice> invoices = List.of(invoice);

        // When
        when(invoiceRepository.findAll()).thenReturn(invoices);
        //  or
        //  doReturn(invoices).when(invoiceRepository).findAll();

        // Then
        List<Invoice> resultedInvoices = invoiceService.getAllInvoices();

        assertNotNull(resultedInvoices, "The list must not be null.");
        assertEquals(1, resultedInvoices.size(), "The list must have one element.");
        assertEquals(1L, resultedInvoices.getFirst().getId(), "The id must be 1.");
        assertEquals(100.0, resultedInvoices.getFirst().getTotal(), "The total must be 100.0.");
        assertEquals(time, resultedInvoices.getFirst().getDate(), "The date must be the current date.");
    }

    @ParameterizedTest
    @ValueSource(longs = {1, 2, 3, 4, 5})
    void getInvoiceById(Long id) {
        // Given
        LocalDateTime time = LocalDateTime.now();

        Invoice invoice = new Invoice();
        invoice.setId(id);
        invoice.setDate(time);
        invoice.setTotal(100.0);

        // When
        when(invoiceRepository.findById(id)).thenReturn(Optional.of(invoice));

        // Then
        Invoice resultedInvoice = invoiceService.getInvoiceById(id);

        assertNotNull(resultedInvoice, "The invoice must not be null.");
        assertEquals(id, resultedInvoice.getId(), "The id must be 1.");
        assertEquals(100.0, resultedInvoice.getTotal(), "The total must be 100.0.");
        assertEquals(time, resultedInvoice.getDate(), "The date must be the current date.");
    }

    @Test
    void getInvoiceById_andInvoiceNotFound() {
        // Given

        // When
        when(invoiceRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        // Then
        assertThrows(InvoiceNotFoundException.class,
                () -> invoiceService.getInvoiceById(1L), "The invoice must not be found.");
    }
}

package com.example.spring_recap.persitence.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class InvoiceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;
}

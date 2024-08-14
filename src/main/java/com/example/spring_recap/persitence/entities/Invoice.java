package com.example.spring_recap.persitence.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;

    private Double total;

    @OneToMany(mappedBy = "invoice",
            cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<InvoiceItem> items;
}

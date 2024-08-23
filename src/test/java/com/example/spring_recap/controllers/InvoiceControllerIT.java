package com.example.spring_recap.controllers;

import com.example.spring_recap.controllers.advice.CustomControllerAdvice;
import com.example.spring_recap.controllers.dtos.InvoiceDto;
import com.example.spring_recap.controllers.dtos.InvoiceItemDto;
import com.example.spring_recap.controllers.dtos.ResponsePayload;
import com.example.spring_recap.services.InvoiceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
//@AutoConfigureMockMvc
class InvoiceControllerIT {

//    @Autowired
    private MockMvc mvc;

    @Autowired
    private InvoiceController invoiceController;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders
                .standaloneSetup(invoiceController)
                .setControllerAdvice(new CustomControllerAdvice())
                .build();
    }

    @Test
    void testAddInvoice() throws Exception {
        // Given
        InvoiceItemDto firstItem = InvoiceItemDto.builder()
                .name("Item 1")
                .price(10.0)
                .quantity(1)
                .build();
        InvoiceItemDto secondItem = InvoiceItemDto.builder()
                .name("Item 2")
                .price(20.0)
                .quantity(2)
                .build();

        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.setItems(List.of(firstItem, secondItem));

        // When

        // Then
        mvc.perform(post("/invoices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(writeObjectAsString(invoiceDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Bogdan", "Bogdan"))
                .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
                .andExpect(cookie().value("Bogdan", "Bogdan"))
                .andExpect(jsonPath("$.message").value("Invoice added successfully"))
                .andExpect(jsonPath("$.data.id").isNotEmpty())
                .andExpect(jsonPath("$.data.date").isNotEmpty())
                .andExpect(jsonPath("$.data.total").isNotEmpty())
                .andExpect(jsonPath("$.data.items[0].id").isNotEmpty())
                .andExpect(jsonPath("$.data.items[1].id").isNotEmpty());

//        String contentAsString = mvcResult.getResponse().getContentAsString();
//        ResponsePayload<LinkedHashMap> responsePayload = readObjectAsString(contentAsString, ResponsePayload.class);
//
//        LinkedHashMap data = responsePayload.data();
//
//        assertNotNull(data.get("id"));
//        assertNotNull(data.get("date"));
//        assertNotNull(data.get("total"));
//
//        ArrayList<LinkedHashMap> items = (ArrayList<LinkedHashMap>) data.get("items");
//        items.forEach(item -> assertNotNull(item.get("id")));
    }

    @Test
    void testGetInvoiceById() throws Exception {
        // Given
        long id = 1L;

        // When

        // Then
        mvc.perform(MockMvcRequestBuilders.get("/invoices/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.items[0].id").isNotEmpty())
                .andExpect(jsonPath("$.items[1].id").isNotEmpty());
    }

    @Test
    void testGetInvoiceById_andInvoiceNotFound() throws Exception {
        // Given
        long id = 2L;

        // When

        // Then
        mvc.perform(MockMvcRequestBuilders.get("/invoices/" + id))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Invoice with id 2 not found"));
    }

    private String writeObjectAsString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private ResponsePayload readObjectAsString(String json, Class<ResponsePayload> clazz) {
        try {
            return new ObjectMapper().readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

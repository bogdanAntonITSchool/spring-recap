package com.example.spring_recap.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DummyServiceTest {

    private DummyService dummyService;
    private List<String> mockedList;

    @BeforeEach
    void setUp() {
        dummyService = new DummyService();
        mockedList = mock(List.class);
    }

    @Test
    void getListElementSize_andElementIsEmpty_thenReturnZero() {
        // Given

        // When
        when(mockedList.size()).thenReturn(10);
        when(mockedList.get(any(Integer.class))).thenReturn("");

        // Then
        int resultSize = dummyService.doSomething(mockedList, 5);

        assertEquals(0, resultSize, "The element must be empty.");
    }

    @Test
    void getListElementSize_andElementIsNotEmpty_thenReturnElementSize() {
        // Given

        // When
        when(mockedList.size()).thenReturn(10);
        when(mockedList.get(any(Integer.class))).thenReturn("Hello, World!");

        // Then
        int resultSize = dummyService.doSomething(mockedList, 5);

        assertEquals(13, resultSize, "The element must have a length of 13.");
    }

}

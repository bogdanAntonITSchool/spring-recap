package com.example.spring_recap.controllers.dtos;

public record ResponsePayload<T>(T data, String message) {
}

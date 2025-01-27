package com.example.demo.model;

public record FeatureMessageDto(
        String message,
        String sender,
        String roomId
) {
}

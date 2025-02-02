package com.example.demo.model;

import java.util.List;

public record Feature(
) {
    public record Response(
            List<Toggle> toggles
    ){}

    public record Toggle(
            String feature,
            boolean isEnabled
    ){}

    public record UpdateResponse(
            String featureId,
            boolean isEnabled
    ) {
    }
}

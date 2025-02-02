package com.example.demo.controller;

import com.example.demo.model.Feature;
import com.example.demo.model.FeatureMessageDto;
import com.example.demo.service.FeatureToggleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/feature")
public class FeatureToggleController {

    private final FeatureToggleService featureToggleService;

    @GetMapping("/toggle")
    public Feature.Response toggle() {
        log.info("toggle request in");
        List<Feature.Toggle> allFeature = featureToggleService.getAllFeature();
        return new Feature.Response(allFeature);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PutMapping("/toggle")
    public Feature.UpdateResponse updateToggle(@RequestParam("toggleId") String toggleId,
                                               @RequestParam("enabled") boolean enabled) {
        featureToggleService.publishChangedToggleStatus(new FeatureMessageDto(String.valueOf(enabled), toggleId));
        return new Feature.UpdateResponse(toggleId, enabled);
    }
}

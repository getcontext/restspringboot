package com.capco.asignment.controller;

import com.capco.asignment.model.Feature;
import com.capco.asignment.service.FeatureService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest Controller for features
 */
@RestController
@RequestMapping(value = "/features", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class FeatureRestController {
    private final FeatureService service;

    @Autowired
    public FeatureRestController(FeatureService featureService) {
        this.service = featureService;
    }

    @GetMapping
    public List<Feature> getAll() {
        return service.getAllFeatures();
    }

    @GetMapping(params = {"isEnabled"})
    public List<Feature> getAllFilteredByEnabled(
            @RequestParam boolean isEnabled
    ) {
        return service.getFeaturesEnabled();
    }

    @GetMapping("/{id}")
    public Feature getById(@PathVariable @Positive(message = "id must be a positive number") Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Feature create(@Valid @RequestBody Feature feature) {
        return service.create(feature);
    }

    @PutMapping("/{id}")
    public Feature update(@RequestBody @Valid Feature feature, @PathVariable @Positive(message = "id must be a positive number") Long id) {
        return service.update(id, feature);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") @Positive(message = "id must be a positive number") Long id) {
        service.delete(id);
    }
}

package com.capco.asignment.service;

import com.capco.asignment.model.Feature;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FeatureServiceImpl implements FeatureService {
    private List<Feature> features = new ArrayList<>(
            Arrays.asList(
                    new Feature(1L, "a", true),
                    new Feature(2L, "b", false),
                    new Feature(3L, "c", true),
                    new Feature(4L, "", false),
                    new Feature(5L, "", true)
            )
    );

    @Override
    public List<Feature> getAllFeatures() {
        return features;
    }

    @Override
    public List<Feature> getFeaturesDisabled() {
        return features.stream()
                .filter(feature -> feature.isEnabled() == false)
                .toList();
    }

    @Override
    public List<Feature> getFeaturesEnabled() {
        return features.stream()
                .filter(feature -> feature.isEnabled() == true)
                .toList();
    }

    @Override
    public Feature getById(Long id) {
        return features.stream()
                .filter(feature -> feature.getId().equals(id))
                .findAny()
                .orElseThrow();
    }

    @Override
    public Feature update(Long id, Feature feature) {
        Feature featureUpdated = getById(id);
        featureUpdated.setName(feature.getName());
        featureUpdated.setEnabled(feature.isEnabled());
        return featureUpdated;
    }

    @Override
    public Feature create(Feature feature) {
        Long newId = features.stream().mapToLong(feature1 -> Long.valueOf(feature1.getId())).max().orElse(0L) + 1L;
        feature.setId(newId);
        features.add(feature);
        return getById(feature.getId());
    }

    @Override
    public void delete(Long id) {
        boolean isDeleted = features.removeIf(feature -> feature.getId().equals(id));
        if(!isDeleted){
            throw new NoSuchElementException();
        }
    }
}

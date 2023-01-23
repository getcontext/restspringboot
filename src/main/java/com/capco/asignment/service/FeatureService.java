package com.capco.asignment.service;

import com.capco.asignment.model.Feature;

import java.util.List;

public interface FeatureService {
    /**
     * Retrieves all features currently existing
     * @return
     */
    List<Feature> getAllFeatures();
    /**
     *
     * @return A list of features disabled
     */
    List<Feature> getFeaturesDisabled();
    /**
     *
     * @return A list of features enabled
     */
    List<Feature> getFeaturesEnabled();
    /**
     *
     * @param id The id of the feature
     * @return The feature with the matching id
     */
    Feature getById(Long id);
    /**
     *
     * @param id The id of the feature to be updated
     * @param request The feature object to be updated
     * @return The updated feature
     */
    Feature update(Long id, Feature request);
    /**
     *
     * @param feature The feature object to be created
     * @return The feature object created
     */
    Feature create(Feature feature);
    /**
     *
     * @param id The id of the feature to be deleted
     */
    void delete(Long id);
}

package com.example.server.service;

import com.example.server.dto.request.ResourceRequest;
import com.example.server.dto.response.ResourceResponse;
import com.example.server.enums.ResourceStatus;

import java.util.List;

public interface ResourceService {
    ResourceResponse createResource(ResourceRequest request);
    ResourceResponse updateResource(String id, ResourceRequest request);
    ResourceResponse getResourceById(String id);
    List<ResourceResponse> getAllResources();
    void deleteResource(String id);

    List<ResourceResponse> getResourcesByType(String type);
    List<ResourceResponse> getResourcesByLocation(String location);
    List<ResourceResponse> getResourcesByStatus(ResourceStatus status);
    List<ResourceResponse> getResourcesByMinCapacity(Integer capacity);
}

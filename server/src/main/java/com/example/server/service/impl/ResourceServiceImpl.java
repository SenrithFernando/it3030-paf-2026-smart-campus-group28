package com.example.server.service.impl;

import com.example.server.dto.request.ResourceRequest;
import com.example.server.dto.response.ResourceResponse;
import com.example.server.enums.ResourceStatus;
import com.example.server.exceptions.ResourceNotFoundException;
import com.example.server.model.Resource;
import com.example.server.repository.ResourceRepository;
import com.example.server.service.ResourceService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResourceServiceImpl implements ResourceService {

    private final ResourceRepository resourceRepository;

    public ResourceServiceImpl(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    @Override
    public ResourceResponse createResource(ResourceRequest request) {
        Resource resource = Resource.builder()
                .name(request.getName())
                .type(request.getType())
                .capacity(request.getCapacity())
                .location(request.getLocation())
                .availabilityWindows(request.getAvailabilityWindows())
                .status(request.getStatus())
                .description(request.getDescription())
                .build();

        Resource saved = resourceRepository.save(resource);
        return mapToResponse(saved);
    }

    @Override
    public void deleteResource(String id) {
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found with id: " + id));
        resourceRepository.delete(resource);
    }


    @Override
    public List<ResourceResponse> getResourcesByType(String type) {
        return resourceRepository.findByTypeContainingIgnoreCase(type).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ResourceResponse> getResourcesByLocation(String location) {
        return resourceRepository.findByLocationContainingIgnoreCase(location).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ResourceResponse> getResourcesByStatus(ResourceStatus status) {
        return resourceRepository.findByStatus(status).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ResourceResponse> getResourcesByMinCapacity(Integer capacity) {
        return resourceRepository.findByCapacityGreaterThanEqual(capacity).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ResourceResponse updateResource(String id, ResourceRequest request) {
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found with id: " + id));

        resource.setName(request.getName());
        resource.setType(request.getType());
        resource.setCapacity(request.getCapacity());
        resource.setLocation(request.getLocation());
        resource.setAvailabilityWindows(request.getAvailabilityWindows());
        resource.setStatus(request.getStatus());
        resource.setDescription(request.getDescription());

        Resource updated = resourceRepository.save(resource);
        return mapToResponse(updated);
    }

    @Override
    public ResourceResponse getResourceById(String id) {
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found with id: " + id));
        return mapToResponse(resource);
    }

    @Override
    public List<ResourceResponse> getAllResources() {
        return resourceRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }





    private ResourceResponse mapToResponse(Resource resource) {
        return ResourceResponse.builder()
                .id(resource.getId())
                .name(resource.getName())
                .type(resource.getType())
                .capacity(resource.getCapacity())
                .location(resource.getLocation())
                .availabilityWindows(resource.getAvailabilityWindows())
                .status(resource.getStatus())
                .description(resource.getDescription())
                .createdAt(resource.getCreatedAt())
                .updatedAt(resource.getUpdatedAt())
                .build();
    }

}

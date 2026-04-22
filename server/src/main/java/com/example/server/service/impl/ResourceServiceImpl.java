package com.example.server.service.impl;

import com.example.server.dto.request.ResourceRequest;
import com.example.server.dto.response.ResourceResponse;
import com.example.server.enums.ResourceStatus;
import com.example.server.exceptions.ResourceNotFoundException;
import com.example.server.model.Resource;
import com.example.server.repository.ResourceRepository;
import com.example.server.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {

    private final ResourceRepository resourceRepository;

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
    public List<ResourceResponse> getResourcesByType(String type) {
        return List.of();
    }

    @Override
    public List<ResourceResponse> getResourcesByLocation(String location) {
        return List.of();
    }

    @Override
    public List<ResourceResponse> getResourcesByStatus(ResourceStatus status) {
        return List.of();
    }

    @Override
    public List<ResourceResponse> getResourcesByMinCapacity(Integer capacity) {
        return List.of();
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
        resource.setUpdatedAt(LocalDateTime.now());

        Resource updated = resourceRepository.save(resource);
        return mapToResponse(updated);
    }

}

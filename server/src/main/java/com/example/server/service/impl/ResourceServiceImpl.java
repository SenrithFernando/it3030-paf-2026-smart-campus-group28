package com.example.server.service.impl;

import com.example.server.dto.request.ResourceRequest;
import com.example.server.dto.response.ResourceResponse;
import com.example.server.model.Resource;
import com.example.server.repository.ResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}

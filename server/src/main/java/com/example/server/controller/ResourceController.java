package com.example.server.controller;

import com.example.server.dto.request.ResourceRequest;
import com.example.server.dto.response.ApiResponse;
import com.example.server.dto.response.ResourceResponse;
import com.example.server.enums.ResourceStatus;
import com.example.server.service.ResourceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/resources")
@Tag(name = "Facilities & Assets", description = "Endpoints for managing campus resources")
public class ResourceController {

    private final ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @PostMapping
    //@PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create a new resource (Admin only)")
    public ResponseEntity<ApiResponse<ResourceResponse>> createResource(@Valid @RequestBody ResourceRequest request) {
        ResourceResponse response = resourceService.createResource(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Resource created successfully", response));
    }

    @PutMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update an existing resource (Admin only)")
    public ResponseEntity<ApiResponse<ResourceResponse>> updateResource(
            @PathVariable String id, @Valid @RequestBody ResourceRequest request) {
        ResourceResponse response = resourceService.updateResource(id, request);
        return ResponseEntity.ok(ApiResponse.success("Resource updated successfully", response));
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete a resource (Admin only)")
    public ResponseEntity<ApiResponse<Void>> deleteResource(@PathVariable String id) {
        resourceService.deleteResource(id);
        return ResponseEntity.ok(ApiResponse.success("Resource deleted successfully", null));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a specific resource by ID")
    public ResponseEntity<ApiResponse<ResourceResponse>> getResource(@PathVariable String id) {
        ResourceResponse response = resourceService.getResourceById(id);
        return ResponseEntity.ok(ApiResponse.success("Resource fetched successfully", response));
    }

    @GetMapping
    @Operation(summary = "Get all resources or filter by parameters")
    public ResponseEntity<ApiResponse<List<ResourceResponse>>> getAllResources(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) ResourceStatus status,
            @RequestParam(required = false) Integer minCapacity
    ) {
        List<ResourceResponse> responses;

        if (type != null) {
            responses = resourceService.getResourcesByType(type);
        } else if (location != null) {
            responses = resourceService.getResourcesByLocation(location);
        } else if (status != null) {
            responses = resourceService.getResourcesByStatus(status);
        } else if (minCapacity != null) {
            responses = resourceService.getResourcesByMinCapacity(minCapacity);
        } else {
            responses = resourceService.getAllResources();
        }

        return ResponseEntity.ok(ApiResponse.success("Resources fetched successfully", responses));
    }

}

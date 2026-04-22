package com.example.server.controller;

import com.example.server.dto.request.ResourceRequest;
import com.example.server.dto.response.ResourceResponse;
import com.example.server.service.ResourceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resources")
@RequiredArgsConstructor
@Tag(name = "Facilities & Assets", description = "Endpoints for managing campus resources")
public class ResourceController {

    private final ResourceService resourceService;

    @PostMapping
    //@PreAuthorize("hasRole('ADMIN')")
    //@Operation(summary = "Create a new resource (Admin only)")
    public ResponseEntity<ApiResponse<ResourceResponse>> createResource(@Valid @RequestBody ResourceRequest request) {
        ResourceResponse response = resourceService.createResource(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Resource created successfully", response));
    }

    @PutMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    //@Operation(summary = "Update an existing resource (Admin only)")
    public ResponseEntity<ApiResponse<ResourceResponse>> updateResource(
            @PathVariable String id, @Valid @RequestBody ResourceRequest request) {
        ResourceResponse response = resourceService.updateResource(id, request);
        return ResponseEntity.ok(ApiResponse.success("Resource updated successfully", response));
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    //@Operation(summary = "Delete a resource (Admin only)")
    public ResponseEntity<ApiResponse<Void>> deleteResource(@PathVariable String id) {
        resourceService.deleteResource(id);
        return ResponseEntity.ok(ApiResponse.success("Resource deleted successfully", null));
    }


}

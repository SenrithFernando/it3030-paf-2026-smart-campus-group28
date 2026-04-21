package com.example.server.dto.request;

import com.example.server.enums.ResourceStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ResourceRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Type is required")
    private String type;

    @NotNull(message = "Capacity is required")
    @Positive(message = "Capacity must be greater than 0")
    private Integer capacity;

    @NotBlank(message = "Location is required")
    private String location;

    @NotBlank(message = "Availability windows are required")
    private String availabilityWindows;

    @NotNull(message = "Status is required")
    private ResourceStatus status;

    private String description;
}


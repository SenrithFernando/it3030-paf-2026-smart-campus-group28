package com.example.server.dto.request;

import org.antlr.v4.runtime.misc.NotNull;

public class ResourceRequest {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Type is required")
    private String type;

    @NotBlank(message = "Location is required")
    private String location;

    @NotBlank(message = "Availability windows are required")
    private String availabilityWindows;

    @NotNull(message = "Capacity is required")
    @Positive(message = "Capacity must be greater than 0")
    private Integer capacity;
}

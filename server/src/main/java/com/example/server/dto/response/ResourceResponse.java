package com.example.server.dto.response;

import com.example.server.enums.ResourceStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ResourceResponse {
    private String id;
    private String name;
    private String type;
    private Integer capacity;
    private String location;
    private String availabilityWindows;
    private ResourceStatus status;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
package com.example.server.dto.response;

import com.example.server.enums.ResourceStatus;
import java.time.LocalDateTime;

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

    public ResourceResponse() {}

    public ResourceResponse(String id, String name, String type, Integer capacity, String location, String availabilityWindows, ResourceStatus status, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.capacity = capacity;
        this.location = location;
        this.availabilityWindows = availabilityWindows;
        this.status = status;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getAvailabilityWindows() { return availabilityWindows; }
    public void setAvailabilityWindows(String availabilityWindows) { this.availabilityWindows = availabilityWindows; }
    public ResourceStatus getStatus() { return status; }
    public void setStatus(ResourceStatus status) { this.status = status; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    // Manual Builder
    public static ResourceResponseBuilder builder() {
        return new ResourceResponseBuilder();
    }

    public static class ResourceResponseBuilder {
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

        public ResourceResponseBuilder id(String id) { this.id = id; return this; }
        public ResourceResponseBuilder name(String name) { this.name = name; return this; }
        public ResourceResponseBuilder type(String type) { this.type = type; return this; }
        public ResourceResponseBuilder capacity(Integer capacity) { this.capacity = capacity; return this; }
        public ResourceResponseBuilder location(String location) { this.location = location; return this; }
        public ResourceResponseBuilder availabilityWindows(String availabilityWindows) { this.availabilityWindows = availabilityWindows; return this; }
        public ResourceResponseBuilder status(ResourceStatus status) { this.status = status; return this; }
        public ResourceResponseBuilder description(String description) { this.description = description; return this; }
        public ResourceResponseBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public ResourceResponseBuilder updatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; return this; }

        public ResourceResponse build() {
            return new ResourceResponse(id, name, type, capacity, location, availabilityWindows, status, description, createdAt, updatedAt);
        }
    }
}
package com.example.server.model;

import com.example.server.enums.ResourceStatus;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "resources")
@EntityListeners(AuditingEntityListener.class)
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private String type; // e.g. LECTURE_HALL, LAB, MEETING_ROOM, EQUIPMENT

    private Integer capacity;

    private String location;

    private String availabilityWindows; // e.g. "08:00-18:00 Weekdays"

    @Enumerated(EnumType.STRING)
    private ResourceStatus status;

    @Column(length = 1000)
    private String description;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public Resource() {}

    public Resource(String id, String name, String type, Integer capacity, String location, String availabilityWindows, ResourceStatus status, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
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
    public static ResourceBuilder builder() {
        return new ResourceBuilder();
    }

    public static class ResourceBuilder {
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

        public ResourceBuilder id(String id) { this.id = id; return this; }
        public ResourceBuilder name(String name) { this.name = name; return this; }
        public ResourceBuilder type(String type) { this.type = type; return this; }
        public ResourceBuilder capacity(Integer capacity) { this.capacity = capacity; return this; }
        public ResourceBuilder location(String location) { this.location = location; return this; }
        public ResourceBuilder availabilityWindows(String availabilityWindows) { this.availabilityWindows = availabilityWindows; return this; }
        public ResourceBuilder status(ResourceStatus status) { this.status = status; return this; }
        public ResourceBuilder description(String description) { this.description = description; return this; }
        public ResourceBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public ResourceBuilder updatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; return this; }

        public Resource build() {
            return new Resource(id, name, type, capacity, location, availabilityWindows, status, description, createdAt, updatedAt);
        }
    }
}

package com.example.server.model;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "resources", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name", name = "uk_resource_name")
}) // Added unique constraint
@EntityListeners(AuditingEntityListener.class)
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, length = 100)
    private String name;

    private String type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ResourceStatus status;

    @Column(length = 2000) // Increased from 1000 to 2000
    private String description;
}

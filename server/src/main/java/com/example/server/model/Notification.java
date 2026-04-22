package com.smartcampus.api.model;

import com.smartcampus.api.enums.NotificationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    private String userId;
    
    @Enumerated(EnumType.STRING)
    private NotificationType type;
    
    private String title;
    
    @Column(length = 1000)
    private String message;
}
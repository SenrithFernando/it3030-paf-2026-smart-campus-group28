package com.smartcampus.api.dto.response;

import com.smartcampus.api.enums.NotificationType;
import lombok.Data;

@Data
public class NotificationResponse {
    private String id;
    private String userId;
    private NotificationType type;
    private String title;
    private String message;
}
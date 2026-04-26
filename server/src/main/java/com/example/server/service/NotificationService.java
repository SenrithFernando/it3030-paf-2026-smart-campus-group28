package com.example.server.service;

import com.example.server.dto.response.NotificationResponse;
import com.example.server.enums.NotificationType;

import java.util.List;

public interface NotificationService {
    void createNotification(String userId, NotificationType type, String title, String message);
    List<NotificationResponse> getUserNotifications(String userId);
    List<NotificationResponse> getUnreadUserNotifications(String userId);
    void markAsRead(String id, String userId);
}
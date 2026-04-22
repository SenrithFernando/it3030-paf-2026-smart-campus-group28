package com.smartcampus.api.service;

import com.smartcampus.api.dto.response.NotificationResponse;
import com.smartcampus.api.enums.NotificationType;

import java.util.List;

public interface NotificationService {
    void createNotification(String userId, NotificationType type, String title, String message);
    List<NotificationResponse> getUserNotifications(String userId);
    List<NotificationResponse> getUnreadUserNotifications(String userId);
    void markAsRead(String id, String userId);
}
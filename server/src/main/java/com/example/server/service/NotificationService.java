package com.smartcampus.api.service;

import com.smartcampus.api.enums.NotificationType;

public interface NotificationService {
    void createNotification(String userId, NotificationType type, String title, String message);
}
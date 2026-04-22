package com.smartcampus.api.service.impl;

import com.smartcampus.api.enums.NotificationType;
import com.smartcampus.api.model.Notification;
import com.smartcampus.api.repository.NotificationRepository;
import com.smartcampus.api.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    public void createNotification(String userId, NotificationType type, String title, String message) {
        Notification notification = Notification.builder()
                .userId(userId)
                .type(type)
                .title(title)
                .message(message)
                .isRead(false)
                .build();
        notificationRepository.save(notification);
    }
}
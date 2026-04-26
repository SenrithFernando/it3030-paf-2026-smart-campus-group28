package com.example.server.controller;

import com.example.server.dto.response.ApiResponse;
import com.example.server.dto.response.NotificationResponse;
import com.example.server.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
@Tag(name = "Notifications", description = "Endpoints for user notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    @Operation(summary = "Get all notifications for the authenticated user")
    public ResponseEntity<ApiResponse<List<NotificationResponse>>> getUserNotifications(
            @RequestParam("userId") String userId) {
        List<NotificationResponse> responses = notificationService.getUserNotifications(userId);
        return ResponseEntity.ok(ApiResponse.success("Notifications fetched successfully", responses));
    }

    @GetMapping("/unread")
    @Operation(summary = "Get all unread notifications for the authenticated user")
    public ResponseEntity<ApiResponse<List<NotificationResponse>>> getUnreadNotifications(
            @RequestParam("userId") String userId) {
        List<NotificationResponse> responses = notificationService.getUnreadUserNotifications(userId);
        return ResponseEntity.ok(ApiResponse.success("Unread notifications fetched successfully", responses));
    }

    @PatchMapping("/{id}/read")
    @Operation(summary = "Mark a specific notification as read")
    public ResponseEntity<ApiResponse<Void>> markAsRead(
            @PathVariable String id,
            @RequestParam("userId") String userId) {
        notificationService.markAsRead(id, userId);
        return ResponseEntity.ok(ApiResponse.success("Notification marked as read", null));
    }
}

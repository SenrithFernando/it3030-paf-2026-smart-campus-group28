package com.smartcampus.api.controller;

import com.smartcampus.api.dto.response.ApiResponse;
import com.smartcampus.api.dto.response.NotificationResponse;
import com.smartcampus.api.security.CustomUserDetails;
import com.smartcampus.api.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        List<NotificationResponse> responses = notificationService.getUserNotifications(userDetails.getUser().getId());
        return ResponseEntity.ok(ApiResponse.success("Notifications fetched successfully", responses));
    }

    @GetMapping("/unread")
    @Operation(summary = "Get all unread notifications for the authenticated user")
    public ResponseEntity<ApiResponse<List<NotificationResponse>>> getUnreadNotifications(
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        List<NotificationResponse> responses = notificationService.getUnreadUserNotifications(userDetails.getUser().getId());
        return ResponseEntity.ok(ApiResponse.success("Unread notifications fetched successfully", responses));
    }
}
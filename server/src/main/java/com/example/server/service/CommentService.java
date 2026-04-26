package com.smartcampus.api.service;

import com.smartcampus.api.dto.request.CommentRequest;
import com.smartcampus.api.dto.response.CommentResponse;

import java.util.List;

public interface CommentService {
    CommentResponse createComment(String ticketId, String userId, CommentRequest request);
    List<CommentResponse> getCommentsByTicketId(String ticketId);
    CommentResponse updateComment(String id, String userId, boolean isAdmin, CommentRequest request);
    void deleteComment(String id, String userId, boolean isAdmin);
}

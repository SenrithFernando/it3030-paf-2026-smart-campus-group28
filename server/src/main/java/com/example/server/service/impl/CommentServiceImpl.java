package com.smartcampus.api.service.impl;

import com.smartcampus.api.dto.request.CommentRequest;
import com.smartcampus.api.dto.response.CommentResponse;
import com.smartcampus.api.enums.NotificationType;
import com.smartcampus.api.exception.ResourceNotFoundException;
import com.smartcampus.api.exception.UnauthorizedException;
import com.smartcampus.api.model.Comment;
import com.smartcampus.api.model.Ticket;
import com.smartcampus.api.repository.CommentRepository;
import com.smartcampus.api.repository.TicketRepository;
import com.smartcampus.api.service.CommentService;
import com.smartcampus.api.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final TicketRepository ticketRepository;
    private final NotificationService notificationService;

    @Override
    public CommentResponse createComment(String ticketId, String userId, CommentRequest request) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found."));

        Comment comment = Comment.builder()
                .ticketId(ticketId)
                .userId(userId)
                .content(request.getContent())
                .build();
                
        Comment saved = commentRepository.save(comment);

        // Notify the ticket owner if the comment was added by someone else
        if (!ticket.getCreatedBy().equals(userId)) {
            String title = "New Comment on your Ticket";
            String message = "A new comment was added to your ticket (" + ticket.getId() + ").";
            notificationService.createNotification(ticket.getCreatedBy(), NotificationType.TICKET_UPDATE, title, message);
        }

        return mapToResponse(saved);
    }

    @Override
    public List<CommentResponse> getCommentsByTicketId(String ticketId) {
        if (!ticketRepository.existsById(ticketId)) {
            throw new ResourceNotFoundException("Ticket not found.");
        }
        
        return commentRepository.findByTicketIdOrderByCreatedAtAsc(ticketId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CommentResponse updateComment(String id, String userId, boolean isAdmin, CommentRequest request) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found."));

        if (!isAdmin && !comment.getUserId().equals(userId)) {
            throw new UnauthorizedException("You do not have permission to edit this comment.");
        }

        comment.setContent(request.getContent());
        comment.setUpdatedAt(LocalDateTime.now());
        
        Comment updated = commentRepository.save(comment);
        return mapToResponse(updated);
    }

    @Override
    public void deleteComment(String id, String userId, boolean isAdmin) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found."));

        if (!isAdmin && !comment.getUserId().equals(userId)) {
            throw new UnauthorizedException("You do not have permission to delete this comment.");
        }

        commentRepository.delete(comment);
    }

    private CommentResponse mapToResponse(Comment comment) {
        return CommentResponse.builder()
                .id(comment.getId())
                .ticketId(comment.getTicketId())
                .userId(comment.getUserId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .build();
    }
}

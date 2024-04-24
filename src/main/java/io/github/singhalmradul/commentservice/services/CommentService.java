package io.github.singhalmradul.commentservice.services;

import java.util.List;
import java.util.UUID;

import io.github.singhalmradul.commentservice.model.Comment;
import io.github.singhalmradul.commentservice.model.CommentRecord;

public interface CommentService {

    long getCommentCountByPostId(UUID postId);

    Comment createComment(UUID postId, UUID userId, String text);

    List<CommentRecord> getAllComments();

    List<CommentRecord> getCommentsByPostId(UUID postId);

    CommentRecord getCommentById(UUID commentId);
}
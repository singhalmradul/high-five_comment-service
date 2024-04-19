package io.github.singhalmradul.commentservice.services;

import java.util.List;
import java.util.UUID;

import io.github.singhalmradul.commentservice.model.CommentRecord;

public interface CommentService {

    long getCommentCountByPostId(UUID postId);

    void comment(UUID postId, UUID userId, String text);

    List<CommentRecord> getAllComments();

    List<CommentRecord> getCommentsByPostId(UUID postId);

}
package io.github.singhalmradul.commentservice.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.github.singhalmradul.commentservice.model.Comment;

public interface CommentRepository extends MongoRepository<Comment, UUID> {
    long countByPostId(UUID postId);

    List<Comment> findByPostIdOrderByCreatedAt(UUID postId);
}
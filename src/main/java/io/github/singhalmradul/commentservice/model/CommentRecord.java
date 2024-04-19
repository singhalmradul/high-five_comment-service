package io.github.singhalmradul.commentservice.model;

import java.time.Instant;
import java.util.UUID;

public record CommentRecord(UUID id, UUID postId, User user, String text, Instant createdAt) {}

package io.github.singhalmradul.commentservice.model;

import java.time.Instant;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "comments")
public class Comment {

    @Id
    UUID id;

    UUID postId;

    String text;

    Instant createdAt;

    UUID userId;
}

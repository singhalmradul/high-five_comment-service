package io.github.singhalmradul.commentservice.model;

import java.util.UUID;

public record HttpPostRequestBody(UUID userId, String text) {}

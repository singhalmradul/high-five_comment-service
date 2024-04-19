package io.github.singhalmradul.commentservice.model;

import java.util.UUID;

public record User(UUID id, String displayName, String avatar) {}
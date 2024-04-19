package io.github.singhalmradul.commentservice.handlers;

import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

public interface CommentHandler {

    ServerResponse getCommentCountByPostId(ServerRequest request);

    ServerResponse comment(ServerRequest request);

    ServerResponse getAllComments(ServerRequest request);

    ServerResponse getCommentsByPostId(ServerRequest request);
}
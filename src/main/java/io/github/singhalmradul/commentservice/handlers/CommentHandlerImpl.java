package io.github.singhalmradul.commentservice.handlers;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.servlet.function.ServerResponse.ok;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebInputException;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import io.github.singhalmradul.commentservice.model.HttpPostRequestBody;
import io.github.singhalmradul.commentservice.services.CommentService;
import jakarta.servlet.ServletException;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor(onConstructor_ = @Autowired)
public class CommentHandlerImpl implements CommentHandler {

    private static final String POST_ID = "postId";

    private final CommentService service;

    @Override
    public ServerResponse getCommentCountByPostId(ServerRequest request) {

        UUID postId = UUID.fromString(request.pathVariable(POST_ID));

        int likesCount = (int) service.getCommentCountByPostId(postId);

        return (
            ok()
            .contentType(APPLICATION_JSON)
            .body(likesCount)
        );
    }

    @Override
    public ServerResponse comment(ServerRequest request) {
        try {

            var postId = UUID.fromString(request.pathVariable(POST_ID));
            var body = request.body(HttpPostRequestBody.class);

            service.comment(postId, body.userId(), body.text());

            return ok().build();

        } catch (ServletException | IOException | IllegalArgumentException | NullPointerException e) {

            throw new ServerWebInputException(e.getMessage());
        }
    }

    @Override
    public ServerResponse getAllComments(ServerRequest request) {

            return (
                ok()
                .contentType(APPLICATION_JSON)
                .body(service.getAllComments())
            );
    }

    @Override
    public ServerResponse getCommentsByPostId(ServerRequest request) {

            UUID postId = UUID.fromString(request.pathVariable(POST_ID));

            return (
                ok()
                .contentType(APPLICATION_JSON)
                .body(service.getCommentsByPostId(postId))
            );
    }
}

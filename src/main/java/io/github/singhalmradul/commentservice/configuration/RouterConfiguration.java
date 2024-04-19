package io.github.singhalmradul.commentservice.configuration;

import static org.springframework.web.servlet.function.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import io.github.singhalmradul.commentservice.handlers.CommentHandler;

@Configuration
public class RouterConfiguration {

    @Bean
    RouterFunction<ServerResponse> commentsRouter(CommentHandler handler) {

        return (
            route()
            .path("/posts/{postId}/comments", builder -> builder
                .GET("/count", handler::getCommentCountByPostId)
                .POST(handler::comment)
                .GET(handler::getCommentsByPostId)
            )
            .path("/comments", builder -> builder
                .GET(handler::getAllComments)
            )
            .build()
        );
    }
}

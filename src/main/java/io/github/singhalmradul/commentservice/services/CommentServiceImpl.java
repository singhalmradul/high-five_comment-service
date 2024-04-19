package io.github.singhalmradul.commentservice.services;

import static java.util.UUID.randomUUID;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebInputException;

import io.github.singhalmradul.commentservice.model.Comment;
import io.github.singhalmradul.commentservice.model.CommentRecord;
import io.github.singhalmradul.commentservice.proxies.PostServiceProxy;
import io.github.singhalmradul.commentservice.proxies.UserServiceProxy;
import io.github.singhalmradul.commentservice.repositories.CommentRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;
    private final UserServiceProxy userServiceProxy;
    private final PostServiceProxy postServiceProxy;

    @Override
    public long getCommentCountByPostId(UUID postId) {

        return repository.countByPostId(postId);
    }

    private void validate(UUID postId, UUID userId) {

        boolean valid = userServiceProxy.existsByUserId(userId)
                && postServiceProxy.existsByPostId(postId);

        if (!valid) {
            throw new ServerWebInputException("Invalid userId or postId");
        }
    }

    @Override
    public void comment(UUID postId, UUID userId, String text) {
        System.out.println("" +
            "\033[1;91m user_id= " + userId
            + " commented \033[43m" +text
            + "\033[1;91m on post_id= " + postId+ ",\033[0m"
        );
        validate(postId, userId);
        repository.save(
            Comment.builder()
                .id(randomUUID())
                .postId(postId)
                .userId(userId)
                .text(text)
                .createdAt(Instant.now())
                .build()
        );
    }

    @Override
    public List<CommentRecord> getAllComments() {
        return repository.findAll().stream().map(this::addData).toList();
    }

    private CommentRecord addData(Comment comment) {
        return new CommentRecord(
            comment.getId(),
            comment.getPostId(),
            userServiceProxy.getUserById(comment.getUserId()),
            comment.getText(),
            comment.getCreatedAt()
        );
    }

    @Override
    public List<CommentRecord> getCommentsByPostId(UUID postId) {
        return repository.findByPostIdOrderByCreatedAt(postId).stream().map(this::addData).toList();
    }
}

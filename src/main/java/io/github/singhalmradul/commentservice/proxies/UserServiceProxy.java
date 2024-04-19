package io.github.singhalmradul.commentservice.proxies;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.github.singhalmradul.commentservice.model.User;

@FeignClient(name = "user-service")
public interface UserServiceProxy {

    @GetMapping("/users/{userId}/exists")
    boolean existsByUserId(@PathVariable("userId") UUID userId);

    @GetMapping("/users/{userId}?view=minimal")
    User getUserById(@PathVariable("userId") UUID userId);
}
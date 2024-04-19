package io.github.singhalmradul.commentservice.proxies;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "post-service")
public interface PostServiceProxy {

    @GetMapping("/posts/{postId}/exists")
    boolean existsByPostId(@PathVariable("postId") UUID postId);
}

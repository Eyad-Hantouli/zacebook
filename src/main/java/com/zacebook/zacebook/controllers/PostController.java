package com.zacebook.zacebook.controllers;

import com.zacebook.zacebook.services.PostService;
import com.zacebook.zacebook.tables.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/posts")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Map<String, Object>> getPosts() {
        return postService.getPosts();
    }

    @GetMapping(path = "/{authorId}")
    public List<Map<String, Object>> getPostsByAuthorId(@PathVariable String authorId) { // User.userName
        return postService.getPostsByAuthorId(authorId);
    }

    @PostMapping
    public void createPost(@RequestBody Map<String, Object> requestedPost) {
        postService.createPost(requestedPost);
    }

    @PutMapping
    public void updatePost(@RequestBody Map<String, Object> requestedPost) {
        postService.updatePost(requestedPost);
    }

    @DeleteMapping(path = "/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
    }
}

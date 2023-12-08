package com.zacebook.zacebook.services;

import com.zacebook.zacebook.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Map<String, Object>> getPosts() {
        return new ArrayList<>();
    }

    public List<Map<String, Object>> getPostsByAuthorId(String authorId) {
        return new ArrayList<>();
    }

    public void createPost(Map<String, Object> requestedPost) {

    }

    public void updatePost(Map<String, Object> requestedPost) {

    }

    public void deletePost(Long postId) {

    }
}

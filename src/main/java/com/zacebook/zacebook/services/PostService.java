package com.zacebook.zacebook.services;

import com.zacebook.zacebook.repositories.PostRepository;
import com.zacebook.zacebook.repositories.UserRepository;
import com.zacebook.zacebook.tables.Post;
import com.zacebook.zacebook.tables.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class PostService extends ObjectSpecializer {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostService(PostRepository postRepository,
                       UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public List<Map<String, Object>> getPosts() {
        List<Post> posts = postRepository.findAll();
        List<Map<String, Object>> respond = new ArrayList<>();
        for (Post post : posts) {
            respond.add(post.getAllData());
        }
        return respond;
    }
    // make down like the top one :).
    public List<Map<String, Object>> getPostsByAuthorId(String authorId) {
        User author = userRepository
                .findById(authorId)
                .orElseThrow(() -> new IllegalStateException("User with user_name = " + authorId + " doesn't exist."));

        List<Post> posts = postRepository.findByAuthor(author);
        List<Map<String, Object>> respond = new ArrayList<>();
        for (Post post : posts) {
            respond.add(post.getAllData());
        }
        return respond;
    }

    public void createPost(Map<String, Object> requestedPost) {
        // -- Fields --
        String authorId = getStringValue(requestedPost, "authorId");

        String textualContent = getStringValue(requestedPost, "textualContent");

        LocalDate creationDate = LocalDate.now();

        // -- Validations --

        // authorId validation
        if (authorId == null) {
            throw new IllegalStateException("user_name mustn't be null.");
        }
        if (authorId.length() < 1) {
            throw new IllegalStateException("user_name length must be at least 1 character.");
        }
        User author = userRepository
                .findById(authorId)
                .orElseThrow(() -> new IllegalStateException("User with user_name = " + authorId + " doesn't exist."));

        // textualContent validation
        if (textualContent == null) {
            throw new IllegalStateException("post content mustn't be null.");
        }
        if (textualContent.length() < 1) {
            throw new IllegalStateException("post must contains at least 1 character.");
        }

        Post post = new Post(textualContent, author, creationDate);

        postRepository.save(post);
    }

    public void updatePost(Map<String, Object> requestedPost) {
        // -- Fields --
        Long postId = getLongValue(requestedPost, "postId");

        String textualContent = getStringValue(requestedPost, "textualContent");

        // -- Validations --

        // postId validation
        if (postId == null) {
            throw new IllegalStateException("post_id mustn't be null.");
        }
        if (postId < 1) {
            throw new IllegalStateException("post_id must be between 1 - " + Long. MAX_VALUE + ".");
        }

        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new IllegalStateException("Post with post_id = " + postId + " doesn't exist."));

        // textualContent validation
        if (textualContent == null) {
            throw new IllegalStateException("post content mustn't be null.");
        }
        if (textualContent.length() < 1) {
            throw new IllegalStateException("post must contains at least 1 character.");
        }

        post.setTextualContent(textualContent);

        postRepository.save(post);
    }

    public void deletePost(Long postId) {
        // postId validation
        if (postId == null) {
            throw new IllegalStateException("post_id mustn't be null.");
        }
        if (postId < 1) {
            throw new IllegalStateException("post_id must be between 1 - " + Long. MAX_VALUE + ".");
        }

        boolean postExist = postRepository.existsById(postId);
        if (!postExist) {
            throw new IllegalStateException("Post with post_id = " + postId + " doesn't exist.");
        }

        postRepository.deleteById(postId);
    }
}

package com.zacebook.zacebook.services;

import com.zacebook.zacebook.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }


    public List<Map<String, Object>> getCommentsByPostId(Long postId) {
        return new ArrayList<>();
    }

    public void createComment(Map<String, Object> requestedComment) {

    }

    public void updateComment(Map<String, Object> requestedComment) {

    }

    public void deleteComment(Long commentId) {

    }
}

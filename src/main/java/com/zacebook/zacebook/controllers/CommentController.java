package com.zacebook.zacebook.controllers;

import com.zacebook.zacebook.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/comments")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping(path = "/{postId}")
    public List<Map<String, Object>> getCommentsByPostId(@PathVariable Long postId) {
        return commentService.getCommentsByPostId(postId);
    }

    @PostMapping
    public void createComment(@RequestBody Map<String, Object> requestedComment) {
        commentService.createComment(requestedComment);
    }

    @PutMapping
    public void updateComment(@RequestBody Map<String, Object> requestedComment) {
        commentService.updateComment(requestedComment);
    }

    @DeleteMapping(path = "/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }

}

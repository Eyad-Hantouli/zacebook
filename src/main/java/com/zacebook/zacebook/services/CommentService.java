package com.zacebook.zacebook.services;

import com.zacebook.zacebook.repositories.CommentRepository;
import com.zacebook.zacebook.repositories.PostRepository;
import com.zacebook.zacebook.repositories.UserRepository;
import com.zacebook.zacebook.tables.Comment;
import com.zacebook.zacebook.tables.Post;
import com.zacebook.zacebook.tables.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CommentService extends ObjectSpecializer{
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentService(
            CommentRepository commentRepository,
            PostRepository postRepository,
            UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }


    public List<Map<String, Object>> getCommentsByPostId(Long postId) {
        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new IllegalStateException("Post with post_id = " + postId + " doesn't exist."));

        List<Comment> comments = commentRepository.findByPost(post);
        List<Map<String, Object>> respond = new ArrayList<>();
        for (Comment comment : comments) {
            respond.add(comment.getAllData());
        }
        return respond;
    }

    public void createComment(Map<String, Object> requestedComment) {
        // -- Fields --
        String textualContent = getStringValue(requestedComment, "textualContent");

        String authorId = getStringValue(requestedComment, "authorId");

        Long postId = getLongValue(requestedComment, "postId");

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

        // postId validation
        if (postId == null) {
            throw new IllegalStateException("post_id mustn't be null.");
        }
        if (postId < 1) {
            throw new IllegalStateException("post_id must be greater than or equal 1.");
        }
        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new IllegalStateException("Post with post_id = " + postId + " doesn't exist."));

        // textualContent validation
        if (textualContent == null) {
            throw new IllegalStateException("comment content mustn't be null.");
        }
        if (textualContent.length() < 1) {
            throw new IllegalStateException("comment must contains at least 1 character.");
        }

        Comment comment = new Comment(textualContent, post, author, creationDate);

        commentRepository.save(comment);
    }

    public void updateComment(Map<String, Object> requestedComment) {
        // -- Fields --
        Long commentId = getLongValue(requestedComment, "commentId");

        String textualContent = getStringValue(requestedComment, "textualContent");

        // -- Validations --

        // commentId validation
        if (commentId == null) {
            throw new IllegalStateException("comment_id mustn't be null.");
        }
        if (commentId < 1) {
            throw new IllegalStateException("comment_id must be between 1 - " + Long. MAX_VALUE + ".");
        }

        Comment comment = commentRepository
                .findById(commentId)
                .orElseThrow(() -> new IllegalStateException("Comment with comment_id = " + commentId + " doesn't exist."));

        // textualContent validation
        if (textualContent == null) {
            throw new IllegalStateException("comment content mustn't be null.");
        }
        if (textualContent.length() < 1) {
            throw new IllegalStateException("comment must contains at least 1 character.");
        }

        comment.setTextualContent(textualContent);

        commentRepository.save(comment);
    }

    public void deleteComment(Long commentId) {
        // commentId validation
        if (commentId == null) {
            throw new IllegalStateException("comment_id mustn't be null.");
        }
        if (commentId < 1) {
            throw new IllegalStateException("comment_id must be between 1 - " + Long. MAX_VALUE + ".");
        }

        boolean commentExist = commentRepository.existsById(commentId);
        if (!commentExist) {
            throw new IllegalStateException("Comment with comment_id = " + commentId + " doesn't exist.");
        }

        commentRepository.deleteById(commentId);
    }
}

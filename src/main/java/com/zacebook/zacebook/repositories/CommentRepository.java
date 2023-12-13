package com.zacebook.zacebook.repositories;

import com.zacebook.zacebook.tables.Comment;
import com.zacebook.zacebook.tables.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
}

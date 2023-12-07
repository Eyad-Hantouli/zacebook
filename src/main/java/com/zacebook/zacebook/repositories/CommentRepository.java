package com.zacebook.zacebook.repositories;

import com.zacebook.zacebook.tables.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}

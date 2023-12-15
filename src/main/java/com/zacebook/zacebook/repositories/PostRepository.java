package com.zacebook.zacebook.repositories;

import com.zacebook.zacebook.tables.Post;
import com.zacebook.zacebook.tables.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByAuthor(User user);
}

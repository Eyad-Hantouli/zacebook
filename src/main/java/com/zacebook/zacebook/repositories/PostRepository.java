package com.zacebook.zacebook.repositories;

import com.zacebook.zacebook.tables.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}

package com.zacebook.zacebook.repositories;

import com.zacebook.zacebook.compositekeys.ReactionKey;
import com.zacebook.zacebook.enums.Reactions;
import com.zacebook.zacebook.tables.Post;
import com.zacebook.zacebook.tables.Reaction;
import com.zacebook.zacebook.tables.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReactionRepository extends JpaRepository<Reaction, ReactionKey> {
    List<Reaction> findByPost(Post post);
    List<Reaction> findByPostAndType(Post post, Reactions type);
    boolean existsByAuthorAndPost(User author, Post post);
    void deleteByAuthorAndPost(User author, Post post);
}

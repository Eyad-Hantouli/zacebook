package com.zacebook.zacebook.repositories;

import com.zacebook.zacebook.compositekeys.ReactionKey;
import com.zacebook.zacebook.tables.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReactionRepository extends JpaRepository<Reaction, ReactionKey> {
}

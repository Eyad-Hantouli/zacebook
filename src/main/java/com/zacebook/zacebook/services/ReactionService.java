package com.zacebook.zacebook.services;

import com.zacebook.zacebook.enums.Reactions;
import com.zacebook.zacebook.repositories.ReactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ReactionService {
    private final ReactionRepository reactionRepository;

    @Autowired
    public ReactionService (ReactionRepository reactionRepository) {
        this.reactionRepository = reactionRepository;
    }


    public List<Map<String, Object>> getReactionsByPostId(Long postId) {
        return new ArrayList<>();
    }

    public List<Map<String, Object>> getReactionsByPostIdAndType(Long postId, Reactions type) {
        return new ArrayList<>();
    }

    public void createReaction(Map<String, Object> requestedReaction) {

    }

    public void deleteReaction(Long postId, String authorId) {

    }
}

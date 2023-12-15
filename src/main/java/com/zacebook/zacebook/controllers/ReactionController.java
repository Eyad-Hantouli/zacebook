package com.zacebook.zacebook.controllers;

import com.zacebook.zacebook.enums.Reactions;
import com.zacebook.zacebook.services.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/reactions")
public class ReactionController {
    private final ReactionService reactionService;

    @Autowired
    public ReactionController(ReactionService reactionService) {
        this.reactionService = reactionService;
    }

    @GetMapping(path = "/{postId}")
    public List<Map<String, Object>> getReactionsByPostId(@PathVariable Long postId) {
        return reactionService.getReactionsByPostId(postId);
    }

    @GetMapping(path = "/{postId}/{type}")
    public List<Map<String, Object>> getReactionsByPostIdAndType(@PathVariable Long postId,
                                                                      @PathVariable String type) {
        return reactionService.getReactionsByPostIdAndType(postId, type);
    }

    @PostMapping
    public void createReaction(@RequestBody Map<String, Object> requestedReaction) {
        reactionService.createReaction(requestedReaction);
    }

    @DeleteMapping(path = "/{postId}/{authorId}")
    public void deleteReaction(@PathVariable Long postId,
                               @PathVariable String authorId // User.userName
    ) {
        reactionService.deleteReaction(postId, authorId);
    }
}

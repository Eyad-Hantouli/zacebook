package com.zacebook.zacebook.services;

import com.zacebook.zacebook.enums.Reactions;
import com.zacebook.zacebook.repositories.PostRepository;
import com.zacebook.zacebook.repositories.ReactionRepository;
import com.zacebook.zacebook.repositories.UserRepository;
import com.zacebook.zacebook.tables.Post;
import com.zacebook.zacebook.tables.Reaction;
import com.zacebook.zacebook.tables.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ReactionService extends ObjectSpecializer {
    private final ReactionRepository reactionRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReactionService (ReactionRepository reactionRepository,
                            PostRepository postRepository,
                            UserRepository userRepository) {
        this.reactionRepository = reactionRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }


    public List<Map<String, Object>> getReactionsByPostId(Long postId) {
        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new IllegalStateException("Post with post_id = " + postId + " doesn't exist."));

        List<Reaction> reactions = reactionRepository.findByPost(post);
        List<Map<String, Object>> respond = new ArrayList<>();
        for (Reaction reaction : reactions) {
            respond.add(reaction.getAllData());
        }
        return respond;
    }

    public List<Map<String, Object>> getReactionsByPostIdAndType(Long postId, String type) {
        // Post validation
        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new IllegalStateException("Post with post_id = " + postId + " doesn't exist."));

        // Type validation
        Reactions thisType;

        if (type == null) {
            throw new IllegalStateException("Reaction type can't be null.");
        }

        try {
            thisType = Reactions.valueOf(type.toUpperCase());
        } catch (Exception exception) {
            throw new IllegalStateException("There is no reaction of type = " + type);
        }

        // Get reactions
        List<Reaction> reactions = reactionRepository.findByPostAndType(post, thisType);
        List<Map<String, Object>> respond = new ArrayList<>();
        for (Reaction reaction : reactions) {
            respond.add(reaction.getAllData());
        }
        return respond;
    }

    public void createReaction(Map<String, Object> requestedReaction) {
        // -- Fields --
        Long postId = getLongValue(requestedReaction, "postId");

        String authorId = getStringValue(requestedReaction, "authorId");

        String typeString = getStringValue(requestedReaction, "type");

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

        // Type validation
        Reactions thisType;

        if (typeString == null) {
            throw new IllegalStateException("Reaction type can't be null.");
        }

        try {
            thisType = Reactions.valueOf(typeString.toUpperCase());
        } catch (Exception exception) {
            throw new IllegalStateException("There is no reaction of type = " + typeString);
        }

        Reaction reaction = new Reaction(author, post, thisType, creationDate);
        reactionRepository.save(reaction);
    }

    @Transactional
    public void deleteReaction(Long postId, String authorId) {
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

        // post validation
        if (postId == null) {
            throw new IllegalStateException("post_id mustn't be null.");
        }
        if (postId < 1) {
            throw new IllegalStateException("post_id must be greater than or equal 1.");
        }
        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new IllegalStateException("Post with post_id = " + postId + " doesn't exist."));

        // delete reaction
        boolean reactionExists = reactionRepository.existsByAuthorAndPost(author, post);

        if (!reactionExists) {
            throw new IllegalStateException("There's no reaction from user_name = + " +
                    authorId + " on post_id = " + postId );
        }

        reactionRepository.deleteByAuthorAndPost(author, post);
    }
}

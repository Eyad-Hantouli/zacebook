package com.zacebook.zacebook.services;

import com.zacebook.zacebook.repositories.ProfilePictureRepository;
import com.zacebook.zacebook.repositories.UserRepository;
import com.zacebook.zacebook.tables.Post;
import com.zacebook.zacebook.tables.ProfilePicture;
import com.zacebook.zacebook.tables.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProfilePictureService extends ObjectSpecializer {
    private final ProfilePictureRepository profilePictureRepository;
    private final UserRepository userRepository;
    @Autowired
    public ProfilePictureService(
            ProfilePictureRepository profilePictureRepository,
            UserRepository userRepository) {
        this.profilePictureRepository = profilePictureRepository;
        this.userRepository = userRepository;
    }

    public List<Map<String, Object>> getProfilePicturesByAuthorId(String authorId) {
        User author = userRepository
                .findById(authorId)
                .orElseThrow(() -> new IllegalStateException("User with user_name = " + authorId + " doesn't exist."));

        List<ProfilePicture> profilePictures = profilePictureRepository.findByAuthor(author);
        List<Map<String, Object>> respond = new ArrayList<>();
        for (ProfilePicture profilePicture : profilePictures) {
            respond.add(profilePicture.getAllData());
        }
        return respond;
    }

    public Map<String, Object> getProfilePictureById(Long profilePictureId) {
        ProfilePicture profilePicture = profilePictureRepository
                .findById(profilePictureId)
                .orElseThrow(() -> new IllegalStateException("Profile picture with id = " + profilePictureId + " doesn't exist."));

        return profilePicture.getAllData();
    }

    public void createProfilePicture(Map<String, Object> requestedProfilePicture) {
        // -- Fields --
        String authorId = getStringValue(requestedProfilePicture, "authorId");

        String link = getStringValue(requestedProfilePicture, "link");

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

        // textualContent validation
        if (link == null) {
            throw new IllegalStateException("link mustn't be null.");
        }
        if (link.length() < 1) {
            throw new IllegalStateException("link must contains at least 1 character.");
        }

        ProfilePicture profilePicture = new ProfilePicture(link, author, creationDate);

        ProfilePicture oldActiveProfilePicture = profilePictureRepository
                .findByIsActive(true)
                .orElse(null);

        if (oldActiveProfilePicture != null) {
            oldActiveProfilePicture.setActive(false);
            profilePictureRepository.save(oldActiveProfilePicture);
        }

        profilePictureRepository.save(profilePicture);

    }

    public void deleteProfilePicture(String authorId) {
        User author = userRepository
                .findById(authorId)
                .orElseThrow(() -> new IllegalStateException("User with user_name = " + authorId + " doesn't exist."));

        ProfilePicture profilePicture = profilePictureRepository
                .findByIsActiveAndAuthor(true, author)
                .orElse(null);

        if (profilePicture != null) {
            profilePicture.setActive(false);
            profilePictureRepository.save(profilePicture);
        }
        else {
            throw new IllegalStateException("User with user_name = " + authorId + " doesn't hava a profile picture.");
        }
    }
}

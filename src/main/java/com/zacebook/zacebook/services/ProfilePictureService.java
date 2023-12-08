package com.zacebook.zacebook.services;

import com.zacebook.zacebook.repositories.ProfilePictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProfilePictureService {
    private final ProfilePictureRepository profilePictureRepository;

    @Autowired
    public ProfilePictureService(ProfilePictureRepository profilePictureRepository) {
        this.profilePictureRepository = profilePictureRepository;
    }

    public List<Map<String, Object>> getProfilePicturesByAuthorId(String authorId) {
        return new ArrayList<>();
    }

    public List<Map<String, Object>> getProfilePictureById(Long profilePictureId) {
        return new ArrayList<>();
    }

    public void createProfilePicture(Map<String, Object> requestedProfilePicture) {

    }

    public void updateProfilePicture(Map<String, Object> requestedProfilePicture) {

    }
}

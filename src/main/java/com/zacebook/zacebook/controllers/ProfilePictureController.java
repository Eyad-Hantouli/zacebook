package com.zacebook.zacebook.controllers;

import com.zacebook.zacebook.services.ProfilePictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/profile_pictures")
public class ProfilePictureController {
    private final ProfilePictureService profilePictureService;

    @Autowired
    public ProfilePictureController(ProfilePictureService profilePictureService) {
        this.profilePictureService = profilePictureService;
    }

    @GetMapping(path = "/user/{authorId}")
    public List<Map<String, Object>> getProfilePicturesByAuthorId(@PathVariable String authorId) {
        return profilePictureService.getProfilePicturesByAuthorId(authorId);
    }

    @GetMapping(path = "/picture/{profilePictureId}")
    public Map<String, Object> getProfilePictureById(@PathVariable Long profilePictureId) {
        return profilePictureService.getProfilePictureById(profilePictureId);
    }

    @PostMapping
    public void createProfilePicture(@RequestBody Map<String, Object> requestedProfilePicture) {
        profilePictureService.createProfilePicture(requestedProfilePicture);
    }

    @DeleteMapping(path = "/user/{authorId}")
    public void updateProfilePicture(@PathVariable String authorId) {
        profilePictureService.deleteProfilePicture(authorId);
    }
}

package com.zacebook.zacebook.repositories;

import com.zacebook.zacebook.tables.Post;
import com.zacebook.zacebook.tables.ProfilePicture;
import com.zacebook.zacebook.tables.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfilePictureRepository extends JpaRepository<ProfilePicture, Long> {
    List<ProfilePicture> findByAuthor(User user);
    Optional<ProfilePicture> findByIsActive(boolean isActive);
    Optional<ProfilePicture> findByIsActiveAndAuthor(boolean isActive, User author);

}

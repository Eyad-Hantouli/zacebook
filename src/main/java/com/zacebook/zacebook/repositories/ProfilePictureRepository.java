package com.zacebook.zacebook.repositories;

import com.zacebook.zacebook.tables.ProfilePicture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfilePictureRepository extends JpaRepository<ProfilePicture, Long> {
}

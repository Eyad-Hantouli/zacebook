package com.zacebook.zacebook.repositories;

import com.zacebook.zacebook.tables.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}

package com.zacebook.zacebook.repositories;

import com.zacebook.zacebook.tables.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, String> {
}

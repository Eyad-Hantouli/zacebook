package com.zacebook.zacebook.repositories;

import com.zacebook.zacebook.enums.RelationShips;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RealtionShipRepository extends JpaRepository<RelationShips, Long> {
}

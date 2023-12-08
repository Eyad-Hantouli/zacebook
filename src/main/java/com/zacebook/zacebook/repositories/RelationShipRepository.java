package com.zacebook.zacebook.repositories;

import com.zacebook.zacebook.compositekeys.RelationShipKey;
import com.zacebook.zacebook.tables.RelationShip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelationShipRepository extends JpaRepository<RelationShip, RelationShipKey> {
}

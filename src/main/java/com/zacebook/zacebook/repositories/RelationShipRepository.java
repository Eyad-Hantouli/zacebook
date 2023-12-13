package com.zacebook.zacebook.repositories;

import com.zacebook.zacebook.compositekeys.RelationShipKey;
import com.zacebook.zacebook.enums.RelationShips;
import com.zacebook.zacebook.tables.RelationShip;
import com.zacebook.zacebook.tables.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RelationShipRepository extends JpaRepository<RelationShip, RelationShipKey> {
    List<RelationShip> findByTargetAndType(User target, RelationShips type);
    Optional<RelationShip> findBySourceAndTargetAndType(User source, User target, RelationShips type);

    Optional<RelationShip> findBySourceAndTarget(User source, User target);
    boolean existsBySourceAndTarget(User source, User target);
    void deleteBySourceAndTarget(User source, User target);
}

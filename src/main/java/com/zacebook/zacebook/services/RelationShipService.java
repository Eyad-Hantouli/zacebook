package com.zacebook.zacebook.services;

import com.zacebook.zacebook.enums.RelationShips;
import com.zacebook.zacebook.repositories.RelationShipRepository;
import com.zacebook.zacebook.repositories.UserRepository;
import com.zacebook.zacebook.tables.RelationShip;
import com.zacebook.zacebook.tables.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RelationShipService extends ObjectSpecializer {
    private final RelationShipRepository relationShipRepository;
    private final UserRepository userRepository;

    @Autowired
    public RelationShipService (
            RelationShipRepository relationShipRepository,
            UserRepository userRepository) {
        this.relationShipRepository = relationShipRepository;
        this.userRepository = userRepository;
    }

    public List<Map<String, Object>> getRelationShipsByTargetId(String targetId,
                                                                RelationShips type) {
        User target = userRepository
                .findById(targetId)
                .orElseThrow(() -> new IllegalStateException("User (target) with user_name = " + targetId + " doesn't exist."));

        if (type == null) {
            throw new IllegalStateException("Relationship type = " + type + " isn't a valid type");
        }

        List<RelationShip> relationShips = relationShipRepository.findByTargetAndType(target, type);
        List<Map<String, Object>> respond = new ArrayList<>();
        for (RelationShip relationShip : relationShips) {
            respond.add(relationShip.getAllData());
        }
        return respond;
    }

    @Transactional
    public void createRelationShip(Map<String, Object> requestedRelationShip) {
        // -- Fields --
        String sourceId = getStringValue(requestedRelationShip, "sourceId");

        String targetId = getStringValue(requestedRelationShip, "targetId");

        String typeString = getStringValue(requestedRelationShip, "type");

        LocalDate creationDate = LocalDate.now();

        // -- Validations --

        // source validation
        if (sourceId == null) {
            throw new IllegalStateException("user_name (source_id) mustn't be null.");
        }
        if (sourceId.length() < 1) {
            throw new IllegalStateException("user_name (source_id) length must be at least 1 character.");
        }
        User source = userRepository
                .findById(sourceId)
                .orElseThrow(() -> new IllegalStateException("User (source) with user_name = " + sourceId + " doesn't exist."));

        // target validation
        if (targetId == null) {
            throw new IllegalStateException("user_name (target_id) mustn't be null.");
        }
        if (targetId.length() < 1) {
            throw new IllegalStateException("user_name (target_id) length must be at least 1 character.");
        }
        User target = userRepository
                .findById(targetId)
                .orElseThrow(() -> new IllegalStateException("User (target) with user_name = " + targetId + " doesn't exist."));

        // source & target validation
        if (targetId.equals(sourceId)) {
            throw new IllegalStateException("User can't create a relationship with himself");
        }

        // type validation
        RelationShips type;

        try {
            type = RelationShips.valueOf(typeString);
        } catch (Exception exception) {
            throw new IllegalStateException("There is no relationship of type = " +
                    getStringValue(requestedRelationShip, "type"));
        }

        if (type == null) {
            throw new IllegalStateException("Relationship type = " + type + " isn't a valid type");
        }

        // do relation
        switch (type) {
            case BLOCK:
                this.blockUser(source, target, creationDate);
                break;
            case FRIEND:
                this.acceptFriend(source, target, creationDate);
                break;
            case FRIEND_REQUEST:
                this.sendFriendRequest(source, target, creationDate);
                break;
        }

    }

    private void blockUser(User source, User target, LocalDate creationDate) {
        Optional<RelationShip> relationShipOptionalTS = relationShipRepository.findBySourceAndTarget(target, source);

        // remove relationship between target and source except "BLOCK"
        if (relationShipOptionalTS.isPresent()) {
            RelationShips relationShipTSType =  relationShipOptionalTS.get().getType();

            if (relationShipTSType != RelationShips.BLOCK) {
                deleteRelationShip(target.getUserName(), source.getUserName());
            }
        }



        Optional<RelationShip> relationShipOptionalST = relationShipRepository.findBySourceAndTarget(source, target);

        // remove relationship between source and target
        if (relationShipOptionalST.isPresent()) {
            deleteRelationShip(source.getUserName(), target.getUserName());
        }

        // create "BLOCK" relationship
        RelationShip relationShip = new RelationShip(source, target, RelationShips.BLOCK, creationDate);

        relationShipRepository.save(relationShip);


    }

    private void acceptFriend(User source, User target, LocalDate creationDate) {
        // check if source has relationship with target
        Optional<RelationShip> relationShipOptionalTS = relationShipRepository.findBySourceAndTarget(target, source);

        if (relationShipOptionalTS.isPresent()) {
            RelationShips relationShipTSType =  relationShipOptionalTS.get().getType();

            // check if source has friend request from target
            if (relationShipTSType == RelationShips.BLOCK) {
                throw new IllegalStateException("No friend request to accept -> This user blocked you.");
            }
            else if (relationShipTSType == RelationShips.FRIEND) {
                throw new IllegalStateException("No friend request to accept -> This user is already friend.");
            }

            // convert 'FRIEND_REQUEST' to 'FRIEND' relationship
            this.deleteRelationShip(target.getUserName(), source.getUserName());

            RelationShip relationShipST = new RelationShip(source, target, RelationShips.FRIEND, creationDate);
            RelationShip relationShipTS = new RelationShip(target, source, RelationShips.FRIEND, creationDate);

            relationShipRepository.save(relationShipST);
            relationShipRepository.save(relationShipTS);

        }
        else {
            throw new IllegalStateException("Can't accept non-existence friend request from user_name = " +
                    target.getUserName());
        }
    }

    private void sendFriendRequest(User source, User target, LocalDate creationDate) {
        // check if source has relationship with target
        Optional<RelationShip> relationShipOptionalST = relationShipRepository.findBySourceAndTarget(source, target);

        if (relationShipOptionalST.isPresent()) {
            RelationShips relationShipSTType =  relationShipOptionalST.get().getType();

            if (relationShipSTType == RelationShips.BLOCK) {
                throw new IllegalStateException("Can't send friend request to blocked user.");
            }
            else if (relationShipSTType == RelationShips.FRIEND) {
                throw new IllegalStateException("Can't send friend request -> You are already friends.");
            }
            else if (relationShipSTType == RelationShips.FRIEND_REQUEST) {
                throw new IllegalStateException("You have already sent friend request to this user.");
            }
            return;
        }

        // check if target has relationship with source
        Optional<RelationShip> relationShipOptionalTS = relationShipRepository.findBySourceAndTarget(target, source);

        if (relationShipOptionalTS.isPresent()) {
            RelationShips relationShipTSType =  relationShipOptionalTS.get().getType();

            if (relationShipTSType == RelationShips.BLOCK) {
                throw new IllegalStateException("Can't send friend request -> this user blocked you.");
            }
            else if (relationShipTSType == RelationShips.FRIEND) {
                throw new IllegalStateException("Can't send friend request -> You are already friends.");
            }
            else if (relationShipTSType == RelationShips.FRIEND_REQUEST) {
                this.acceptFriend(source, target, creationDate);
            }
            return;
        }

        RelationShip relationShip = new RelationShip(source, target, RelationShips.FRIEND_REQUEST, creationDate);
        relationShipRepository.save(relationShip);
    }

    @Transactional
    public void deleteRelationShip(String sourceId,
                                   String targetId) {
        // source validation
        if (sourceId == null) {
            throw new IllegalStateException("user_name (source_id) mustn't be null.");
        }
        if (sourceId.length() < 1) {
            throw new IllegalStateException("user_name (source_id) length must be at least 1 character.");
        }
        User source = userRepository
                .findById(sourceId)
                .orElseThrow(() -> new IllegalStateException("User (source) with user_name = " + sourceId + " doesn't exist."));

        // source validation
        if (targetId == null) {
            throw new IllegalStateException("user_name (target_id) mustn't be null.");
        }
        if (targetId.length() < 1) {
            throw new IllegalStateException("user_name (target_id) length must be at least 1 character.");
        }
        User target = userRepository
                .findById(targetId)
                .orElseThrow(() -> new IllegalStateException("User (target) with user_name = " + targetId + " doesn't exist."));


        boolean relationShipExists = relationShipRepository.existsBySourceAndTarget(source, target);

        if (!relationShipExists) {
            throw new IllegalStateException("There's no relationship between user_name = " +
                    targetId + " and user = " + sourceId + ".");
        }

        relationShipRepository.deleteBySourceAndTarget(source, target);
    }
}

package com.zacebook.zacebook.services;

import com.zacebook.zacebook.enums.RelationShips;
import com.zacebook.zacebook.repositories.RelationShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RelationShipService {
    private final RelationShipRepository relationShipRepository;

    @Autowired
    public RelationShipService (RelationShipRepository relationShipRepository) {
        this.relationShipRepository = relationShipRepository;
    }

    public List<Map<String, Object>> getRelationShipsByTargetId(String targetId,
                                                                RelationShips type) {
        return new ArrayList<>();
    }

    public void createRelationShip(Map<String, Object> requestedRelationShip) {

    }

    public void deleteRelationShip(String targetId,
                                   String sourceId) {

    }
}

package com.zacebook.zacebook.controllers;

import com.zacebook.zacebook.enums.RelationShips;
import com.zacebook.zacebook.services.RelationShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/relation_ships")
public class RelationShipController {
    private final RelationShipService relationShipService;

    @Autowired
    public RelationShipController(RelationShipService relationShipService) {
        this.relationShipService = relationShipService;
    }

    @GetMapping(path = "/{targetId}/{type}")
    public List<Map<String, Object>> getRelationShipsByTargetId(@PathVariable String targetId, // User.userName
                                                                @PathVariable RelationShips type) {
        return relationShipService.getRelationShipsByTargetId(targetId, type);
    }

    @PostMapping
    public void createRelationShip(@RequestBody Map<String, Object> requestedRelationShip) {
        relationShipService.createRelationShip(requestedRelationShip);
    }

    @DeleteMapping(path = "/{targetId}/{sourceId}")
    public void deleteRelationShip(@PathVariable String targetId,  // User.userName
                                   @PathVariable String sourceId // User.userName
    ) {
        relationShipService.deleteRelationShip(targetId, sourceId);
    }


}

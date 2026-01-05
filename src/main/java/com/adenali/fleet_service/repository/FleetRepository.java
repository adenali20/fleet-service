package com.adenali.fleet_service.repository;

import com.adenali.fleet_service.domain.Fleet;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.List;


public interface FleetRepository extends MongoRepository<Fleet, String> {
    List<Fleet> findByOwnerId(String ownerId);
}
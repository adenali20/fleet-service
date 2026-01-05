package com.adenali.fleet_service.repository;

import com.adenali.fleet_service.domain.Owner;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface OwnerRepository extends MongoRepository<Owner, String> {
}
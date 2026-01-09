package com.adenali.fleet_service.repository;

import com.adenali.fleet_service.domain.Fleet;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;


public interface FleetRepository extends MongoRepository<Fleet, String> {
    Optional<Fleet> findByPlate(String plate);

    Page<Fleet> findAll(Pageable pageable);

    List<Fleet> findFleetByIdAfter(String after, Pageable pageable);
}

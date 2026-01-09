package com.adenali.fleet_service.repository;

import com.adenali.fleet_service.domain.Device;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface DeviceRepository extends MongoRepository<Device, String> {
    List<Device> findByFleetId(String fleetId);
}

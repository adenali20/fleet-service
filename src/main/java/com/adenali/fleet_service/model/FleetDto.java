package com.adenali.fleet_service.model;


import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class FleetDto {

    private String id;

    private String type;
    private String name;
    private String model;
    private Integer year;
    private String brand;
    private String plate;

    private OwnerDto owner;

    private Map<String, Object> metadata;

    private List<DeviceDto> devices;

    private Instant createdAt;
    private Instant updatedAt;
}

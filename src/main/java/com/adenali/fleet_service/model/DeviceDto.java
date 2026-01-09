package com.adenali.fleet_service.model;


import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.Map;

@Data
@Builder
public class DeviceDto {

    private String id;
    private String fleetId;

    private String name;
    private String description;

    private String carrier;
    private String network;

    private String number;
    private Boolean activated;

    private Map<String, Object> metadata;

    private Instant createdAt;
    private Instant updatedAt;
}

